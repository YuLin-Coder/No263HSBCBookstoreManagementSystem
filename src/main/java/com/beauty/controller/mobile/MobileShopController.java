package com.beauty.controller.mobile;

import com.beauty.mapper.OrderShopDao;
import com.beauty.model.OrderShopEntity;
import com.beauty.model.ShopEntity;
import com.beauty.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("mobileShop")
public class MobileShopController {
    @Autowired
    private ShopService shopService;


    @Autowired
    private OrderShopDao orderShopDao;

    @RequestMapping("info.html")
    public String info(String id,String customerId, Model model)throws Exception{
        ShopEntity shopEntity =  shopService.selectById(id);
        model.addAttribute("shop",shopEntity);
        model.addAttribute("id",id);

        List<OrderShopEntity> list = orderShopDao.list(id);
        model.addAttribute("list",list);
        model.addAttribute("customerId",customerId);
        return "mobile/shop_info";
    }


}
