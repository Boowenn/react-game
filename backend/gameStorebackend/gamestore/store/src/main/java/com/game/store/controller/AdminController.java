package com.game.store.controller;

import com.game.store.utils.JsonResult;
import com.game.store.entity.Admin;
import com.game.store.service.IAdminService;
import com.game.store.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @description 处理用户请求的控制器
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private IAdminService adminService;

    //用户登陆
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public JsonResult<Admin> userLogin(Admin admin, HttpSession session, String code){

        String token = RandomUtil.UUID36();
        System.out.println(token);
//        //执行登陆操作
        Admin LoginUser = adminService.adminLogin(admin);
        LoginUser.setToken(token);
        session.setAttribute("token", token);
        System.out.println("登录万得到信息===>");
        System.out.println(session.getAttribute("token"));
        //返回数据
        return new JsonResult<>(OK,LoginUser);
//        return new JsonResult<>(OK);
    }

}
