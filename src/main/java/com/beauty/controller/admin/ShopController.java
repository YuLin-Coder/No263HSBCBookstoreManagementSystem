package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.Result;
import com.beauty.model.ShopEntity;
import com.beauty.service.ShopService;
import com.beauty.service.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品管理
 */
@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService service;

    @Autowired
    private ShopTypeService shopTypeService;


    /**
     * 在售商品界面
     *
     * @param model
     * @return
     */
    @RequestMapping("list.htm")
    public String list(Model model) {
        EntityWrapper<ShopEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_del",0)
                .eq("is_inventory",0);
        List<ShopEntity> list = service.selectList(wrapper);
        model.addAttribute("list", list);
        return "shop/list";
    }


    /**
     * 在售商品删除
     *
     */
    @PostMapping("del.htm")
    @ResponseBody
    public Result del(String id){
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(id);
        shopEntity.setIsInventory(1);
        service.updateById(shopEntity);
        return Result.success("保存成功");
    }


}
