package com.game.store.service;

import com.game.store.entity.Address;

import java.util.List;


/**
 * @description 收货地址业务层接口
 */
public interface IAddressService {

    /**
     * 添加用户地址
     * @param uid      用户uid
     * @param username 用户名
     * @param address  地址
     */
    void addAddress(Integer uid, String username, Address address);

    /**
     * 根据用户的uid获取用户的收货地址信息集
     * @param uid   用户的uid
     * @return 返回用户的地址集合
     */
    List<Address> getAddressByUid(Integer uid);

    /**
     *　修改用户选中地址为默认收货地址
     * @param uid 用户uid
     * @param aid 收货地址id
     * @param username 操作人
     * @return void
     */
    void setDefault(Integer uid,Integer aid,String username);

    /**
     * 按照收货地址id删除收货地址
     * @param aid 收货地址id
     */
    void deleteAddressByAid(Integer aid, Integer uid, String username);

    /**
     * 根据aid查询用户收货地址
     * @param aid
     * @return
     */
    Address findAddressByAid(Integer aid);
}
