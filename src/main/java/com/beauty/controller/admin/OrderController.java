package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.Result;
import com.beauty.config.utile.DateUtil;
import com.beauty.model.OrderEntity;
import com.beauty.model.OrderShopEntity;
import com.beauty.service.CustomerService;
import com.beauty.service.OrderService;
import com.beauty.service.OrderShopService;
import com.beauty.service.ShopService;
import com.beauty.table.OrderTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 订单接口
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderShopService orderShopService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShopService shopService;


    /**
     * 界面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("page.htm")
    public String page(Model model) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.orderBy(OrderTable.TIME, false);
        List<OrderEntity> list = orderService.selectList(entityWrapper);
        if (list != null) {
            for (OrderEntity orderEntity : list) {
                entityWrapper = new EntityWrapper();
                entityWrapper.eq("order_id", orderEntity.getId());
                List<OrderShopEntity> shoppingGatEntities = orderShopService.selectList(entityWrapper);
                orderEntity.setOrderShops(shoppingGatEntities);
            }
        }
        model.addAttribute("list", list);
        return "order/list";
    }


    /**
     * 修改
     *
     * @param orderEntity
     * @return
     * @throws Exception
     */
    @RequestMapping("update.htm")
    @ResponseBody
    public Result update(OrderEntity orderEntity) throws Exception {
        orderService.updateById(orderEntity);
        return Result.success("1");
    }


    @GetMapping("count.htm")
    public String orderCount(Model model) throws Exception {
        int userCount = customerService.selectCount(new EntityWrapper<>());
        int shopCount = shopService.selectCount(new EntityWrapper<>());
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;

        Date nowTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(nowTime);
        String startTime = format + " 00:00:00";
        String endTime = format + " 24:00:00";

        String startMonth = DateUtil.getFirstDayOfMonth(month);
        String endMonth = DateUtil.getLastDayOfMonth(month);

        EntityWrapper<OrderEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("status", 1)
                .le("time", endTime)
                .ge("time", startTime);
        List<OrderEntity> orderEntities = orderService.selectList(wrapper);
        BigDecimal dayMoney = new BigDecimal(0);
        for (OrderEntity orderEntity : orderEntities) {
            dayMoney = dayMoney.add(orderEntity.getPrice());
        }

        EntityWrapper<OrderEntity> wrappers = new EntityWrapper<>();
        wrappers.eq("status", 1)
                .le("time", endMonth)
                .ge("time", startMonth);
        List<OrderEntity> orderEntities1 = orderService.selectList(wrappers);
        BigDecimal monthMoney = new BigDecimal(0);
        for (OrderEntity orderEntity : orderEntities1) {
            monthMoney = monthMoney.add(orderEntity.getPrice());
        }

        model.addAttribute("userCount", userCount);
        model.addAttribute("shopCount", shopCount);
        model.addAttribute("dayMoney", dayMoney);
        model.addAttribute("monthMoney", monthMoney);
        return "order/count";
    }


}
