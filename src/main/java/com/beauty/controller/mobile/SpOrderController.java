package com.beauty.controller.mobile;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.beauty.config.IdWorkerUtil;
import com.beauty.config.Result;
import com.beauty.config.contants.Contants;
import com.beauty.mapper.OrderShopDao;
import com.beauty.model.*;
import com.beauty.service.*;
import com.beauty.table.OrderTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 */
@Controller
@RequestMapping("order")
public class SpOrderController {

    public static final String PAYURL = "http://localhost:8080/pay?id=%s&name=%s&amount=%s";
    @Autowired
    private ShoppingGatService shoppingGatService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderShopService orderShopService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerAddressService customerAddressService;

    @Autowired
    private OrderShopDao orderShopDao;

    @Autowired
    @Qualifier("alipayService")
    private AlipayService alipayService;
    @Autowired
    public Jedis jedis;

    /**
     * 评价界面
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("comment.do")
    public String comment(String id, Model model) throws Exception {
        OrderShopEntity orderShopEntity = orderShopService.selectById(id);
        model.addAttribute("entity", orderShopEntity);
        return "mobile/comment";
    }


    @RequestMapping("commentData.do")
    @ResponseBody
    public Result commentData(OrderShopEntity orderShopEntity) throws Exception {
        String content = orderShopEntity.getContent();
        content = content.replace("操", "*");
        content = content.replace("sb", "**");
        content = content.replace("傻", "*");
        orderShopEntity.setContent(content);
        orderShopService.updateById(orderShopEntity);
        orderShopEntity = orderShopService.selectById(orderShopEntity.getId());
        Double num = orderShopDao.num(orderShopEntity.getShopId());
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(orderShopEntity.getShopId());
        shopEntity.setScore(num);
        shopService.updateById(shopEntity);
        return Result.success(1);
    }


    /**
     * 收货
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("shouhuo.do")
    @ResponseBody
    public Result shouhuo(String id) throws Exception {
        OrderEntity orderEntity = orderService.selectById(id);
        orderEntity.setStatus(3);
        orderService.updateById(orderEntity);
        return Result.success("欢迎下次光临");
    }

    /**
     * 退货收货
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("tuihuo.do")
    @ResponseBody
    public Result tuihuo(String id) throws Exception {
        orderService.deleteById(id);
        return Result.success("下次再买 - -");
    }

    /**
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("list.do")
    public String list(Model model) throws Exception {
        CustomerEntity userEntity = Contants.getCustomer();
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(OrderTable.CUSTOMER_ID, userEntity.getId()).orderBy("time", false);
        List<OrderEntity> orders = orderService.selectList(wrapper);
        if (orders != null) {
            for (OrderEntity order : orders) {
                wrapper = new EntityWrapper();
                wrapper.eq("order_id", order.getId());
                List<OrderShopEntity> orderShops = orderShopService.selectList(wrapper);
                order.setOrderShops(orderShops);
            }
        }
        model.addAttribute("orders", orders);
        return "mobile/orderList";
    }


    /**
     * 订单结算
     *
     * @param ids  购物车ids
     * @param nums 数量
     * @return
     * @throws Exception
     */
    @RequestMapping("jiesuan.do")
    @ResponseBody
    public Result jiesuan(String ids[], Integer nums[], String prices[], OrderEntity orderEntity, String addressId) throws Exception {
        CustomerEntity userEntity = Contants.getCustomer();
        String orderId = IdWorkerUtil.getId();
        jedis.set("WSY-ORDERID", orderId);
        orderEntity.setId(orderId);
        orderEntity.setCustomerId(userEntity.getId());
        orderEntity.setStatus(0);
        orderEntity.setTime(new Date());
        orderEntity.setOrderId(String.valueOf(IdWorker.getId()));
        CustomerAddressEntity customerAddressEntity = customerAddressService.selectById(addressId);
        if (customerAddressEntity == null) {
            return Result.error("请选择收货地址");
        }
        //验证库存是否足够
        for (int i = 0; i < ids.length; i++) {
            ShoppingGatEntity shoppingGatEntity = shoppingGatService.selectById(ids[i]);
            ShopEntity shopEntity = shopService.selectById(shoppingGatEntity.getShopId());
            if (shopEntity.getStock() == null || shopEntity.getStock() < shoppingGatEntity.getNum()) {
                return Result.error(shopEntity.getName() + "库存不足");
            }
        }

        orderEntity.setAddress(customerAddressEntity.getAddress());
        orderEntity.setName(customerAddressEntity.getName());
        orderEntity.setPhone(customerAddressEntity.getPhone());
        orderService.insert(orderEntity);
        String name = StrUtil.EMPTY;
        for (int i = 0; i < ids.length; i++) {
            ShoppingGatEntity shoppingGatEntity = shoppingGatService.selectById(ids[i]);
            ShopEntity shopEntity = shopService.selectById(shoppingGatEntity.getShopId());
            OrderShopEntity orderShopEntity = new OrderShopEntity();
            orderShopEntity.setId(IdWorkerUtil.getId());
            orderShopEntity.setPrice(new BigDecimal(prices[i]));
            orderShopEntity.setNum(nums[i]);
            orderShopEntity.setOrderId(orderEntity.getId());
            orderShopEntity.setName(shopEntity.getName());
            name = name.concat(shopEntity.getName() + "+");
            orderShopEntity.setCover(shopEntity.getCover());
            orderShopEntity.setShopId(shopEntity.getId());
            orderShopEntity.setCustomerId(userEntity.getId());
            orderShopService.insert(orderShopEntity);
            shopEntity.setStock(shopEntity.getStock() - nums[i]);
            shopService.updateById(shopEntity);

        }
        jedis.set("WSY-SHOPID", name);

        /** 到这里就默认支付成功了 */

        OrderEntity orderEntityNew = orderService.selectById(orderId);
        orderEntityNew.setStatus(1);
        orderService.updateById(orderEntityNew);

        return Result.success("订单结算成功");
    }

}
