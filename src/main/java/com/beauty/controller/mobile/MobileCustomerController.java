package com.beauty.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beauty.config.contants.Contants;
import com.beauty.config.IdWorkerUtil;
import com.beauty.config.Result;
import com.beauty.model.CustomerEntity;
import com.beauty.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户个人信息
 */
@Controller
@RequestMapping("mobileCustomer")
public class MobileCustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 我的界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("jump.html")
    public String my(Model model,String url)throws Exception{
        model.addAttribute("url",url);
        return "mobile/jump";
    }

    /**
     * 我的Data
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("infoData.do")
    @ResponseBody
    public Result infoData(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        return Result.success(customerEntity);
    }


    /**
     * 我的界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("info.do")
    public String info(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        model.addAttribute("customer",customerEntity);
        return "mobile/my";
    }


    /**
     * 我的界面 -- 修改
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("myUpdate.do")
    public String my_update(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        model.addAttribute("entity",customerEntity);
        return "mobile/my_update";
    }

    /**
     * 我的界面 -- 修改
     * @return
     * @throws Exception
     */
    @RequestMapping("myUpdateData.do")
    @ResponseBody
    public Result myUpdateData( CustomerEntity customerEntity)throws Exception{
        customerEntity.setId(Contants.getCustomer().getId());
        customerService.updateById(customerEntity);
        return Result.success("成功");
    }


    /**
     * 登陆界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("login.html")
    public String login(Model model) throws Exception{
        return "mobile/login";
    }


    /**
     * 退出登陆
     * @return
     * @throws Exception
     */
    @RequestMapping("logOut.do")
    @ResponseBody
    public Result logOut( HttpServletResponse response)throws Exception{
        Cookie cookie = new Cookie("login_key_auth_customer","-1");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return Result.success("退出成功");
    }

    /**
     * 登陆
     * @return
     * @throws Exception
     */
    @RequestMapping("loginData.html")
    @ResponseBody
    public Result loginData(String phone,String password, HttpServletResponse response)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("phone",phone);
        CustomerEntity customerEntity = customerService.selectOne(entityWrapper);
        if(customerEntity==null){
            return Result.error("用户不存在");
        }
        if(!customerEntity.getPassword().equals(password)){
            return Result.error("密码错误");
        }
        Cookie cookie = new Cookie("login_key_auth_customer",customerEntity.getId());
        cookie.setPath("/");
        cookie.setMaxAge(3600000);
        response.addCookie(cookie);
        return Result.success(customerEntity.getId(),"登陆成功");
    }

    /**
     * 登陆界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("reg.html")
    public String reg(Model model) throws Exception{
        return "mobile/reg";
    }


    /**
     * 注册
     * @return
     * @throws Exception
     */
    @RequestMapping("regData.html")
    @ResponseBody
    public Result regData( CustomerEntity customerEntity, HttpServletResponse response)throws Exception{
        customerEntity.setId(IdWorkerUtil.getId());
        customerEntity.setHeader("/img/a5.jpg");
        customerService.insert(customerEntity);
        Cookie cookie = new Cookie("login_key_auth_customer",customerEntity.getId());
        cookie.setPath("/");
        cookie.setMaxAge(3600000);
        response.addCookie(cookie);
        return Result.success(customerEntity.getId(),"登陆成功");
    }




}
