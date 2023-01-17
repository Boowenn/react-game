package com.game.store.service;

import com.game.store.entity.Admin;

/**
 * @description 处理用户操作的业务层接口
 */
public interface IAdminService {

    /**
     * 用户登陆操作
     *
     * @param admin 用户信息
     * @return 返回用户
     */
    Admin adminLogin(Admin admin);
}
