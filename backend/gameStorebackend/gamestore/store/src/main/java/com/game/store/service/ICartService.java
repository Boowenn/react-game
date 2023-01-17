package com.game.store.service;

import com.game.store.vo.CartVo;
import com.game.store.entity.Cart;

import java.util.Date;
import java.util.List;

/**
 * @description 处理购物车的接口
 */
public interface ICartService {

    /**
     * 将数据加入购物车
     * @param cart 购物车数据
     * @param createdUser 创建人
     * @param createdTime 创建时间
     * @param modifiedUser 操作人
     * @param modifiedTime 操作时间
     */
    void addCart(Cart cart, String createdUser, Date createdTime, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户uid查询购物车数据
     * @param uid 用户uid
     * @return
     */
    List<CartVo> findAllCartByUid(Integer uid);

    /**
     * 按照cid删除购物车数据
     * @param cid cid
     */
    void deleteCartByCid(Integer cid);


    /**
     * 更新购物车商品数量
     * @param cid
     * @param num
     * @param modifiedUser
     */
    void updateCartNumByCid(Integer cid,Integer num,String modifiedUser);

    /**
     * 按照cids查询购物车数据
     * @param cids
     * @return
     */
    List<CartVo> getVoByCid(Integer[] cids);

    Cart findCartByCid(Integer cid);

}
