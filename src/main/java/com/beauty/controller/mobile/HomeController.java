package com.beauty.controller.mobile;

import com.beauty.config.contants.Contants;
import com.beauty.model.CustomerEntity;
import com.beauty.service.CustomerService;
import com.beauty.service.ShopService;
import com.beauty.service.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 */
@Controller
public class HomeController {

    @Autowired
    private ShopTypeService shopTypeService;

    @Autowired
    private ShopService shopService;



    @Autowired
    private CustomerService customerService;


    /**
     * 首页
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/")
    public String home(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        if(customerEntity!=null){
            model.addAttribute("customer",customerEntity);
        }
        return "shoping/index";
    }


}
