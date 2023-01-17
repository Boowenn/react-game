package com.game.store.controller;

import com.game.store.entity.User;
import com.game.store.service.IUserService;
import com.game.store.service.ex.ValidCodeNotMatchException;
import com.game.store.utils.JsonResult;
import com.google.code.kaptcha.Constants;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @description 处理用户请求的控制器
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    //用户注册
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public JsonResult<Void> userRegister(User user, HttpSession session, String code) {
        //从session取出验证码
        String validCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //判断验证码是否正确
        if (!validCode.equals(code)) {
            throw new ValidCodeNotMatchException("验证码错误,请重试！");
        }
        //执行插入操作
        userService.userRegister(user);
        return new JsonResult<>(OK);
    }
    //用户登陆
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public  JsonResult<User> userLogin(User user,HttpSession session,String code){
        //从session取出验证码
        String validCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //判断验证码是否正确
        if (!validCode.equals(code)) {
            throw new ValidCodeNotMatchException("验证码错误,请重试！");
        }
        //执行登陆操作
        User LoginUser = userService.userLogin(user);
        //将用户名和uid保存到session中
        session.setAttribute("uid",LoginUser.getUid());
        session.setAttribute("username", LoginUser.getUsername());
        //返回数据
        return new JsonResult<>(OK,LoginUser);
    }

    //用户更改密码
    @RequestMapping(value = "/change_password",method = RequestMethod.POST)
    public JsonResult<Void> changePassword(@RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword,
                                           HttpSession session){
        //获取Uid
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePasswrod(uid,username,oldPassword,newPassword);

        //在用户修改密码之后清除session中保存的密码
        session.setAttribute("uid",null);
        return new JsonResult<>(OK,"修改密码成功");
    }
    //修改用户信息
    @RequestMapping(value = "/change_info",method = RequestMethod.POST)
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        //User数据只有四部分   用户电话邮箱性别
        System.out.println(user.getUsername()+user.getEmail()+ user.getPhone()+user.getGender());
        //Service内部已经重新写入
        userService.changeInfo(getUserIdFromSession(session),getUsernameFromSession(session),user);
        return new JsonResult<>(OK,"修改信息成功");
    }
    //获取用户信息
    @RequestMapping(value = "/get_by_uid",method = RequestMethod.GET)
    public JsonResult<User> getByUid(HttpSession session){
        Integer uid = getUserIdFromSession(session);

        User user = userService.getByUid(uid);

        //将用户名、id、电话、邮箱、性别进行回传
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUid(user.getUid());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setAvatar(user.getAvatar());
        return new JsonResult<>(OK,newUser);
    }

    // getUser
    @RequestMapping(value = "/getUser/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public JsonResult<PageInfo<User>> getUser(@PathVariable("pageNum") Integer pageNum,
                                              @PathVariable("pageSize") Integer pageSize,
                                              HttpSession session){

        System.out.println("kkkkkk+++++");

        if (session.getAttribute("token") == null) {
            return new JsonResult<>(OK, "admin已失效");
        } else {
            PageInfo<User> data = userService.getUser(pageNum, pageSize);

            System.out.println(data);
            return new JsonResult<>(OK,data);
        }
    }

    //delete用户
    @RequestMapping(value = "/delete_user",method = RequestMethod.POST)
    public JsonResult<Void> deleteUser(@RequestParam("uid") Integer uid,HttpSession session){
        System.out.println(uid);
        if (session.getAttribute("token") == null) {
            return new JsonResult<>(OK, "admin已失效");
        } else {
            userService.deleteUser(uid);
            return new JsonResult<>(OK,"该用户已成功删除");
        }
    }

}
