package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.IdWorkerUtil;
import com.beauty.config.Result;
import com.beauty.model.ShopTypeEntity;
import com.beauty.service.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类管理
 */
@Controller
@RequestMapping("shoptype")
public class TypeController {

    @Autowired
    private ShopTypeService service;

    /**
     * 界面
     * @param model
     * @return
     */
    @RequestMapping("list.htm")
    public String list(Model model){
        List<ShopTypeEntity> list = service.selectList(new EntityWrapper<>());
        model.addAttribute("list",list);
        return "shoptype/list";
    }


    /**
     * 保存界面
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("save.htm")
    public String save(Model model,String id)throws Exception{
        ShopTypeEntity entity = new ShopTypeEntity();
        if(!StringUtils.isEmpty(id)){
            entity = service.selectById(id);
        }
        model.addAttribute("entity",entity);
        return "shoptype/save";
    }

    /**
     * 保存
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("saveData.htm")
    @ResponseBody
    public Result save(Model model, ShopTypeEntity entity)throws Exception{
        if(StringUtils.isEmpty(entity.getId())){
            entity.setId(IdWorkerUtil.getId());
            service.insert(entity);
        }else{
            service.updateById(entity);
        }
        return Result.success("保存成功");
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("del.htm")
    @ResponseBody
    public Result del(String id)throws Exception{
        service.deleteById(id);
        return Result.success("保存成功");
    }

}
