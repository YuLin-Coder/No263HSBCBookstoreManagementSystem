package com.beauty.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.beauty.config.contants.Contants;
import com.beauty.config.Result;
import com.beauty.model.CustomerAddressEntity;
import com.beauty.model.CustomerEntity;
import com.beauty.model.ShopEntity;
import com.beauty.model.ShoppingGatEntity;
import com.beauty.service.*;
import com.beauty.table.ShoppingGatTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 购物车
 */
@Controller
@RequestMapping("shoppingGat")
public class ShoppingGatController {

    @Autowired
    private ShoppingGatService shoppingGatService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private CustomerService customerService;


    /**
     * 购物车界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("list.do")
    public String list(Model model)throws Exception{
        CustomerEntity userEntity = Contants.getCustomer();
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(ShoppingGatTable.CUSTOMER_ID,userEntity.getId());
        List<ShoppingGatEntity> gats = shoppingGatService.selectList(entityWrapper);
        if(gats!=null ){
            for (ShoppingGatEntity gat : gats) {
                ShopEntity shopEntity = shopService.selectById(gat.getShopId());
                gat.setShopEntity(shopEntity);
            }
        }
        model.addAttribute("gats",gats);
        CustomerEntity customerEntity = customerService.selectById(userEntity.getId());
        model.addAttribute("integral",customerEntity.getIntegral());
       List<CustomerAddressEntity> customerAddressEntities =   customerAddressService.selectList(entityWrapper);
        model.addAttribute("customerAddressEntities",customerAddressEntities);
        return "mobile/gat";
    }


    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public Result delete(String id)throws Exception{
        CustomerEntity userEntity = Contants.getCustomer();
        if(StringUtils.isEmpty(id)){
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq(ShoppingGatTable.CUSTOMER_ID,userEntity.getId());
            shoppingGatService.delete(entityWrapper);
        }else{
            shoppingGatService.deleteById(id);
        }
        return Result.success("成功");
    }

    /**
     * 添加到购物车
     * @param shopId
     * @return
     * @throws Exception
     */
    @RequestMapping("add.do")
    @ResponseBody
    public Result add(String shopId,Integer num,String customerId)throws Exception{
        CustomerEntity userEntity = Contants.getCustomer();
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(ShoppingGatTable.CUSTOMER_ID,userEntity.getId())
                .eq(ShoppingGatTable.SHOP_ID,shopId);
        ShoppingGatEntity gatEntity = shoppingGatService.selectOne(entityWrapper);
        if(gatEntity!=null){
            gatEntity.setNum(gatEntity.getNum()+num);
            shoppingGatService.updateById(gatEntity);
        }else{
            gatEntity = new ShoppingGatEntity();
            gatEntity.setId(IdWorker.get32UUID());
            gatEntity.setNum(num);
            gatEntity.setShopId(shopId);
            gatEntity.setCustomerId(userEntity.getId());
            gatEntity.setCid(customerId);
            shoppingGatService.insert(gatEntity);
        }
        return Result.success("成功");
    }



}
