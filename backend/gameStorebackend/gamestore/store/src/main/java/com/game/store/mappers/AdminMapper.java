package com.game.store.mappers;

import com.game.store.entity.Admin;

/**
 * @description User实体类对应的Mapper接口
 */
public interface AdminMapper {

    /**
     * 根据用户查询用户信息
     *
     * @param username 用户名
     * @return 返回User实体类
     */
    Admin findUserByUsername(String username);


    Admin findUserByUsernameAndPwd(String username, String password);

}
