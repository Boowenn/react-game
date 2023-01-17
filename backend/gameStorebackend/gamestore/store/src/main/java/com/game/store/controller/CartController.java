package com.game.store.controller;

import com.game.store.service.ICartService;
import com.game.store.utils.JsonResult;
import com.game.store.vo.CartVo;
import com.game.store.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @description 处理购物车请求的控制类
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "/add_cart",method = RequestMethod.POST)
    public JsonResult<Void> addCart(Integer pid, Integer price, Integer num, HttpSession session){
        System.out.println(pid+"*"+price+":"+num);
        //从session获取uid和username
        String username = getUsernameFromSession(session);
        Integer uid = getUserIdFromSession(session);
        Date currentTime = new Date();
        //设置参数
        Cart cart = new Cart();
        cart.setUid(uid);
        cart.setPid(pid);
        cart.setPrice(Long.valueOf(price));
        cart.setNum(num);
        System.out.println(cart);
        //执行插入操作
        cartService.addCart(cart,username,currentTime,username,currentTime);
        return new JsonResult<>(OK);
    }
    @RequestMapping(value = "/show_carts",method = RequestMethod.GET)
    public JsonResult<List<CartVo>> showCarts(HttpSession session){
        //获取uid
        Integer uid = getUserIdFromSession(session);
        //获取数据
        List<CartVo> data = cartService.findAllCartByUid(uid);
        //返回数据
        return new JsonResult<>(OK,data);
    }

    @RequestMapping(value = "/delete_cart",method = RequestMethod.POST)
    public JsonResult<Void> showCarts(Integer cid){
        cartService.deleteCartByCid(cid);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/update_num",method = RequestMethod.POST)
    public JsonResult<Void> updateNum(Integer cid,Integer num,HttpSession session){
        String modifiedUser = getUsernameFromSession(session);
        cartService.updateCartNumByCid(cid,num,modifiedUser);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<CartVo>> getCartCids(Integer[] cids){
        List<CartVo> data = cartService.getVoByCid(cids);
        return new JsonResult<>(OK,data);
    }

}
