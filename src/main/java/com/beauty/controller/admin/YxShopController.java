package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.IdWorkerUtil;
import com.beauty.config.Result;
import com.beauty.mapper.YxSupplierDao;
import com.beauty.model.ShopEntity;
import com.beauty.model.ShopTypeEntity;
import com.beauty.model.YxSupplierEntity;
import com.beauty.service.ShopService;
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
 * 进货商品管理
 */
@Controller
@RequestMapping("yx_shop")
public class YxShopController {

    @Autowired
    private ShopService service;

    @Autowired
    private ShopTypeService shopTypeService;
    @Autowired
    private YxSupplierDao yxSupplierDao;

    /**
     * 进货商品界面
     */
    @RequestMapping("list.htm")
    public String list(Model model) {
        EntityWrapper<ShopEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_del", 0)
                .eq("is_inventory", 1);
        List<ShopEntity> list = service.selectList(wrapper);

        for (ShopEntity shopEntity : list) {
            YxSupplierEntity yxSupplierEntity = yxSupplierDao.selectById(shopEntity.getSupperId());
            shopEntity.setSupperName(yxSupplierEntity.getName());
        }

        model.addAttribute("list", list);
        return "stock/list";
    }

    /**
     * 库存信息
     */
    @RequestMapping("list_inventory.htm")
    public String inventory(Model model) {
        EntityWrapper<ShopEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_del", 0);
        List<ShopEntity> list = service.selectList(wrapper);
        model.addAttribute("list", list);
        return "inventory/list";
    }


    /**
     * 进货商品保存界面
     *
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("save.htm")
    public String save(Model model, String id) throws Exception {
        ShopEntity entity = new ShopEntity();
        entity.setStatus(true);
        entity.setHot(false);
        if (!StringUtils.isEmpty(id)) {
            entity = service.selectById(id);
        }
        model.addAttribute("entity", entity);

        List<ShopTypeEntity> types = shopTypeService.selectList(new EntityWrapper<>());
        model.addAttribute("types", types);


        EntityWrapper<YxSupplierEntity> wrappers = new EntityWrapper<>();
        wrappers.eq("is_del", 0);
        List<YxSupplierEntity> supplierEntityList = yxSupplierDao.selectList(wrappers);
        model.addAttribute("supplier", supplierEntityList);


        return "stock/save";
    }

    /**
     * 进货商品保存
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("saveData.htm")
    @ResponseBody
    public Result save(Model model, ShopEntity entity) throws Exception {
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(IdWorkerUtil.getId());
            entity.setStock(0);
            entity.setScore(0.0);
            service.insert(entity);
        } else {
            service.updateById(entity);
        }
        return Result.success("保存成功");
    }

    /**
     * 进货商品删除
     */
    @PostMapping("del.htm")
    @ResponseBody
    public Result del(String id) throws Exception {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(id);
        shopEntity.setIsDel(1);
        service.updateById(shopEntity);
        return Result.success("保存成功");
    }

    /**
     * 进货商品删除
     */
    @PostMapping("shelves.htm")
    @ResponseBody
    public Result shelves(String id) throws Exception {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(id);
        shopEntity.setIsInventory(0);
        service.updateById(shopEntity);
        return Result.success("保存成功");
    }


    /**
     * 在售商品保存
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("stock.htm")
    @ResponseBody
    public Result stock(Model model, Integer stock, String id, Integer type) throws Exception {
        ShopEntity entity = service.selectById(id);
        if (entity.getStock() == null) {
            entity.setStock(0);
        }
        if (type == 1) { //添加库存
            entity.setStock(entity.getStock() + stock);
        } else { //减少库存
            if (entity.getStock() - stock < 0) {
                return Result.error("库存不够");
            }
            entity.setStock(entity.getStock() - stock);
        }
        service.updateById(entity);
        return Result.success("保存成功");
    }

}
