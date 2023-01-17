package com.game.store.service.Impl;

import com.game.store.service.ICartService;
import com.game.store.service.ex.DeleteException;
import com.game.store.service.ex.UpdateException;
import com.game.store.vo.CartVo;
import com.game.store.entity.Cart;
import com.game.store.mappers.CartMapper;
import com.game.store.service.ex.CartInfoNotExistsException;
import com.game.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description 购物车业务层接口的实现类
 */
@Service
public class ICartServiceImpl implements ICartService {
    @Autowired(required = false)
    private CartMapper cartMapper;


    @Override
    public void addCart(Cart cart, String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
        //查询当前商品是否在购物车存在
        Integer pid =  cart.getPid();
        Integer uid =  cart.getUid();
        Cart destCart = cartMapper.findCartByUidAndPid(uid, pid);
        //判断查询结果
        if(destCart==null){//如果不存在
            //补全四个字段
            cart.setCreatedUser(createdUser);
            cart.setModifiedUser(modifiedUser);
            cart.setCreatedTime(createdTime);
            cart.setModifiedTime(modifiedTime);

            //执行插入操作
            Integer integer = cartMapper.addCart(cart);
            //判断插入结果
            if(integer!=1){
                throw new InsertException("插入购物车数据时遭遇未知异常");
            }
        }else{//表示该商品存在数据
            //取出查询的数据数量
            Integer destNum = destCart.getNum();
            //取出新添加产品的数量
            Integer cartNum = cart.getNum();
            //计算结果
            Integer num = destNum + cartNum;

            //执行更新操作
            Integer row = cartMapper.updateCartInfo(destCart.getCid(),num,modifiedUser,modifiedTime);

            if(row != 1 ){
                throw new UpdateException("更新购物车数据是遭遇未知异常");
            }
        }
    }

    @Override
    public List<CartVo> findAllCartByUid(Integer uid) {
        return cartMapper.findAllCartByUid(uid);
    }

    @Override
    public void deleteCartByCid(Integer cid) {
        //删除
        Integer row = cartMapper.deleteCartByCid(cid);
        //判断删除结果
        if(row!=1){
            throw new DeleteException("购物车数据删除异常!");
        }
    }
    @Override
    public void updateCartNumByCid(Integer cid, Integer num, String modifiedUser) {
        //查询购物车数据
        Cart res = cartMapper.findCartByCid(cid);
        if(res==null){
            throw new CartInfoNotExistsException("购物车数据不存在");
        }
        //添加
        Integer row = cartMapper.updateCartNumByCid(num, cid, modifiedUser, new Date());
        //判断
        if(row!=1){
            throw new UpdateException("更新购物车商品数目出现未知异常");
        }
    }

    @Override
    public List<CartVo> getVoByCid(Integer[] cids) {
        //查询数据
        List<CartVo> data = cartMapper.findVoByCid(cids);
        //传输数据
        return data;
    }

    @Override
    public Cart findCartByCid(Integer cid) {
        return cartMapper.findCartByCid(cid);
    }


}
