package com.beauty.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.beauty.config.imlog.ImLog;
import com.beauty.config.Result;
import com.beauty.entity.RoleEntity;
import com.beauty.entity.UserEntity;
import com.beauty.mapper.UserDao;
import com.beauty.service.RoleService;
import com.beauty.service.UserService;
import com.beauty.table.RoleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 后台管理管理
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * 用户界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("list.htm")
    public String list(Model model)throws Exception{
        List<UserEntity> list = userDao.list();
        model.addAttribute("list",list);
        return "user/list";
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
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(RoleTable.STATUS,true);
        List<RoleEntity> entities = roleService.selectList(entityWrapper);
        model.addAttribute("roles",entities);

        UserEntity userEntity = new  UserEntity();
        userEntity.setSex(true);
        userEntity.setStatus(true);
        if(!StringUtils.isEmpty(id)){
            userEntity = userService.selectById(id);
        }
        model.addAttribute("userEntity",userEntity);
        return "user/save";
    }

    /**
     * 保存
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("saveData.htm")
    @ResponseBody
    @ImLog(type = "用户",mark = "保存用户 {name}")
    public Result save(Model model, UserEntity userEntity)throws Exception{
        if(StringUtils.isEmpty(userEntity.getId())){
            userEntity.setId(IdWorker.get32UUID());
            userEntity.setTime(new Date());
            userService.insert(userEntity);
        }else{
            userService.updateById(userEntity);
        }
        return Result.success("保存成功");
    }

    /**
     * 保存
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("del.htm")
    @ResponseBody
    @ImLog(type = "用户",mark = "删除用户 {id}")
    public Result del(Model model, String id )throws Exception{
        userService.deleteById(id);
        return Result.success("保存成功");
    }



}
