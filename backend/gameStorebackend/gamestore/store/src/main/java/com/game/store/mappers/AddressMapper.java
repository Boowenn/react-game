package com.game.store.mappers;


import com.game.store.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0.0
 * @desciption Address所对应的Mapper接口
 */
public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address 收货地址
     * @return {@link Integer} 受影响的行数
     */
    Integer addAddress(Address address);

    /**
     * 根据用户id统计用户收货地址数量
     * @param uid 用户uid
     * @return {@link Integer} 返回当前用户的收货地址总数
     */
    Integer userAddressCount(Integer uid);

    /**
     * 根据用户的uid查询用户的收货地址集合
     * @param uid 用户uid
     * @return 返回收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据用户收货地址aid查询收货地址
     * @param aid 收货地址id
     * @return 返回地址
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户uid将所有地址设为非默认地址
     * @param uid 用户uid
     * @return 返回影响行数
     */
    Integer updateNoneDefault(Integer uid);

    /**
     * 按照aid将该条收货地址设为默认地址
     * @param aid 用户收货地址aid
     * @param modifiedUser 执行操作的操作人
     * @param modifiedTime 执行操作的操作时间
     * @return 返回影响行数
     */
    Integer updateDefault(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 按照aid删除用户的收货地址
     * @param aid 用户的收货地址id
     * @return 返回影响行数
     */
    Integer DeleteAddressByAid(Integer aid);
}
