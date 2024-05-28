package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.IdWorkerUtil;
import com.beauty.config.Result;
import com.beauty.model.AppointmentTypeEntity;
import com.beauty.service.AppointmentTypeService;
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
@RequestMapping("appointment_type")
public class AppointmentTypeController {

    @Autowired
    private AppointmentTypeService appointmentTypeService;

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
        List<AppointmentTypeEntity> list = appointmentTypeService.selectList(entityWrapper);
        model.addAttribute("list",list);
        return "appointmentType/appointment_type_list";
    }

    /**
     * 界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("add.htm")
    public String add(Model model,String id) throws Exception {
        AppointmentTypeEntity entity = new AppointmentTypeEntity();
        if(!StringUtils.isEmpty(id)){
            entity = appointmentTypeService.selectById(id);
        }
        model.addAttribute("list",entity);
        return "appointmentType/appointment_type_add";
    }


    /**
     * 保存数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("save.htm")
    @ResponseBody
    public Result addDatax(AppointmentTypeEntity appointmentTypeEntity) throws Exception {

        if(StringUtils.isEmpty(appointmentTypeEntity.getId())){
            appointmentTypeEntity.setId(IdWorkerUtil.getId());
            appointmentTypeEntity.setCreatedTime(new Date());
            appointmentTypeEntity.setIsDel(0);
            appointmentTypeService.insert(appointmentTypeEntity);
        }else{
            appointmentTypeService.updateById(appointmentTypeEntity);
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
        AppointmentTypeEntity appointmentTypeEntity = new AppointmentTypeEntity();
        appointmentTypeEntity.setId(id);
        appointmentTypeEntity.setIsDel(1);
        appointmentTypeService.updateById(appointmentTypeEntity);

        /** 删除这个地方用户的软删除
         *
         * 软删除  并不是真正的删除，用户看不到，数据库中还有
         * */
        return Result.success("保存成功");
    }


    /**
     * 添加公告的时候使用

     */
    @PostMapping("typeList.htm")
    @ResponseBody
    public Result typeList(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("is_del",0);
        List<AppointmentTypeEntity> list =  appointmentTypeService.selectList(entityWrapper);
        return Result.success(list);
    }
}
