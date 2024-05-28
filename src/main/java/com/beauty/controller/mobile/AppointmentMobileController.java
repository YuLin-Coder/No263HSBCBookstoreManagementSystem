package com.beauty.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.model.AppointmentEntity;
import com.beauty.model.AppointmentTypeEntity;
import com.beauty.service.AppointmentService;
import com.beauty.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 公告
 */
@Controller
@RequestMapping("app_mobile")
public class AppointmentMobileController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentTypeService appointmentTypeService;

    /**
     * 前台列表页面
     */
    @RequestMapping("list")
    public String homeList(Model model) throws Exception {

        //查询分类
        EntityWrapper entityWrapper1 = new EntityWrapper();
        entityWrapper1.eq("is_del", 0);
        List<AppointmentTypeEntity> typeList = appointmentTypeService.selectList(entityWrapper1);
        model.addAttribute("typeList", typeList);

        //查询分类
        EntityWrapper entityWrapper2 = new EntityWrapper();
        entityWrapper2.eq("is_del", 0);
        List<AppointmentEntity> appointmentEntityList = appointmentService.selectList(entityWrapper2);

        model.addAttribute("appList", appointmentEntityList);

        return "mobile/appointmentHome";
    }

    /**
     * 单个页面
     */
    @GetMapping("/detail")
    public String info(String id, Model model) throws Exception {
        AppointmentEntity appointmentEntity = appointmentService.selectById(id);
        model.addAttribute("appointment", appointmentEntity);
        model.addAttribute("id", id);
        return "mobile/appointmentDetail";
    }

}
