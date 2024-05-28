package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.IdWorkerUtil;
import com.beauty.config.Result;
import com.beauty.model.AppointmentEntity;
import com.beauty.model.AppointmentTypeEntity;
import com.beauty.service.AppointmentService;
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
 * 公告接口
 */
@Controller
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentTypeService appointmentTypeService;

    /**
     * 列表界面
     */
    @GetMapping("list")
    public String list(Model model)  {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("is_del",0);
        List<AppointmentEntity> list = appointmentService.selectList(entityWrapper);
        model.addAttribute("list",list);
        return "appointment/appointment_list";
    }

    /**
     * 添加界面
     *
     */
    @GetMapping("add_view")
    public String add(Model model,String id) {
        AppointmentEntity entity = new AppointmentEntity();
        if(!StringUtils.isEmpty(id)){
            entity = appointmentService.selectById(id);
        }
        model.addAttribute("list",entity);

        List<AppointmentTypeEntity> types =  appointmentTypeService.selectList(new EntityWrapper<>());
        model.addAttribute("types",types);

        return "appointment/appointment_add";
    }


    /**
     * 保存数据
     *
     */
    @RequestMapping("save")
    @ResponseBody
    public Result addDatax(AppointmentEntity appointmentEntity) throws Exception {

        if(StringUtils.isEmpty(appointmentEntity.getId())){
            appointmentEntity.setId(IdWorkerUtil.getId());
            appointmentEntity.setCreatedTime(new Date());
            appointmentEntity.setIsDel(0);
            appointmentService.insert(appointmentEntity);
        }else{
            appointmentService.updateById(appointmentEntity);
        }

        return Result.success("保存成功");
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public Result deleteData(String id){
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(id);
        appointmentEntity.setIsDel(1);
        appointmentService.updateById(appointmentEntity);
        return Result.success("保存成功");
    }
}
