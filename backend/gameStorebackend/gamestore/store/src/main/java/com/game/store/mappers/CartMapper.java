package com.game.store.mappers;

import com.game.store.vo.CartVo;
import com.game.store.entity.Cart;

import java.util.Date;
import java.util.List;

/**
 * @description 购物车持久层的Mapper接口
 */
public interface CartMapper {

    /**
     * 插入Cart数据
     * @param cart 购物车数据
     * @return
     */
    Integer addCart(Cart cart);

    /**
     * 更新Cart内容
     * @param cid 购物车id
     * @param num 更新物品数量
     * @param modifiedUser 更新人
     * @param modifiedTime 更新时间
     * @return
     */
    Integer updateCartInfo(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 按照用户的uid和商品的pid查找某条购物车数据
     * @param uid 用户的uid
     * @param pid 商品的id
     * @return 返回购物车数据
     */
    Cart findCartByUidAndPid(Integer uid,Integer pid);

    /**
     * 按照用户uid 查询所有的购物车记录
     * @param uid 用户uid
     * @return 返回购物车集合
     */
    List<CartVo> findAllCartByUid(Integer uid);

    /**
     * 按照购物车id 删除购物车内容物
     * @param cid 购物车id
     * @return 返回影响行数
     */
    Integer deleteCartByCid(Integer cid);

    /**
     * 按照cid查询Cart
     * @param cid
     * @return
     */
    Cart findCartByCid(Integer cid);

    /**
     * 按照cid增减购物车商品的数量
     * @param num   数量
     * @param cid   购物车id
     * @param modifiedUser 操作人
     * @param modifiedTime 操作时间
     * @return
     */
    Integer updateCartNumByCid(Integer num,Integer cid,String modifiedUser,Date modifiedTime);

    /**
     * 按照cids查询购物车数据
     * @param cids
     * @return
     */
    List<CartVo> findVoByCid(Integer[] cids);
}
