package com.game.store.controller;

import com.game.store.entity.Address;
import com.game.store.service.IAddressService;
import com.game.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description 地址操作的控制层
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    /**
     * 处理用户添加地址的操作
     * @param address 添加的地址
     * @param session 项目自动生成的Session
     * @return {@link JsonResult}<{@link Void}>
     */
    @PostMapping
    public JsonResult<Void> addAddress(Address address, HttpSession session){
        //从Session中获取Uid和Username
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        //补全用户的信息
        address.setUid(uid);
        System.out.println(address);
        //添加
        addressService.addAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    /**
     * 处理网页端自动显示用户收货地址的请求
     * @param session 项目自动生成的session
     * @return 返回JsonResult<List<Address>>
     */
    @GetMapping
    public JsonResult<List<Address>> queryAllAddress(HttpSession session){
        List<Address> res = addressService.getAddressByUid(getUserIdFromSession(session));
        return new JsonResult<>(OK,res);
    }

    /**
     * 处理设置默认地址的请求
     * @param aid 被设置为默认的收货地址id
     * @param session 项目启动时生成的session
     * @return 返回void
     */
    @RequestMapping(value = "/set_default/{aid}",method = RequestMethod.POST)
    public JsonResult<Void> setDefault(@PathVariable("aid")Integer aid,HttpSession session){
        //获取uid，username
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        //设置默认
        addressService.setDefault(uid,aid,username);
        return new JsonResult<>(OK);
    }

    /**
     * 处理删除地址的请求
     * @param aid 需要删除的收货地址的id
     * @return 返回OK
     */
    @RequestMapping(value = "/delete_address/{aid}",method = RequestMethod.POST)
    public JsonResult<Void> deleteAddressByAid(@PathVariable("aid")Integer aid,HttpSession session){
        //获取uid
        Integer uid = getUserIdFromSession(session);
        //获取username
        String username = getUsernameFromSession(session);
        //执行删除
        addressService.deleteAddressByAid(aid,uid,username);
        //执行成功返回数据
        return new JsonResult<>(OK);
    }

}
