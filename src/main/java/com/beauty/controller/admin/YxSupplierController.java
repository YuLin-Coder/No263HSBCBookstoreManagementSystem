package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.IdWorkerUtil;
import com.beauty.config.Result;
import com.beauty.model.YxSupplierEntity;
import com.beauty.service.YxSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 公告类型
 */
@Controller
@RequestMapping("yx_supplier")
public class YxSupplierController {

    @Autowired
    private YxSupplierService yxSupplierService;

    /**
     * 界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("list.htm")
    public String list(Model model) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("is_del",0);
        List<YxSupplierEntity> list = yxSupplierService.selectList(entityWrapper);
        model.addAttribute("list",list);
        return "supplier/supplier_list";
    }

    /**
     * 界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("add.htm")
    public String add(Model model,String id) throws Exception {
        YxSupplierEntity entity = new YxSupplierEntity();
        if(!StringUtils.isEmpty(id)){
            entity = yxSupplierService.selectById(id);
        }
        model.addAttribute("list",entity);
        return "supplier/supplier_add";
    }


    /**
     * 保存数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("save.htm")
    @ResponseBody
    public Result addDatax(YxSupplierEntity yxSupplierEntity) throws Exception {

        if(StringUtils.isEmpty(yxSupplierEntity.getId())){
            yxSupplierEntity.setId(IdWorkerUtil.getId());
            yxSupplierEntity.setCreatedTime(new Date());
            yxSupplierEntity.setIsDel(0);
            yxSupplierService.insert(yxSupplierEntity);
        }else{
            yxSupplierService.updateById(yxSupplierEntity);
        }


        return Result.success("保存成功");
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("deleteData.htm")
    @ResponseBody
    public Result deleteData(String id){
        YxSupplierEntity yxSupplierEntity = new YxSupplierEntity();
        yxSupplierEntity.setId(id);
        yxSupplierEntity.setIsDel(1);
        yxSupplierService.updateById(yxSupplierEntity);

        /** 删除这个地方用户的软删除
         *
         * 软删除  并不是真正的删除，用户看不到，数据库中还有
         * */
        return Result.success("保存成功");
    }

}
