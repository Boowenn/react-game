package com.game.store.service;

import com.github.pagehelper.PageInfo;
import com.game.store.entity.User;

/**
 * @description 处理用户操作的业务层接口
 */
public interface IUserService {
    /**
     * 处理用户注册
     * @param user 用户信息
     */
    void userRegister(User user);

    /**
     * 用户登陆操作
     * @param user 用户信息
     * @return 返回用户
     */
    User userLogin(User user);

    /**
     * 更改用户密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 用户的旧密码
     * @param newPasswrod 用户的新密码
     */
    void changePasswrod(Integer uid,String username,String oldPassword,String newPasswrod);

    /**
     * 根据用户的id查询用户的数据
     * @param uid 用户id
     * @return 返回查询到的用户 或者 null
     */
    User getByUid(Integer uid);

//    List<User> getUser();

    PageInfo<User> getUser(Integer pageNum, Integer pageSize);

    /**
     *  更新用户的数据操作
     * @param uid 用户的id
     * @param username 用户名
     * @param user 用户对象数据
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 更新用户的头像操作
     * @param uid   用户id
     * @param avatar 用户头像路径
     * @param username  修改的执行者
     */
    void changeAvatar(Integer uid,String avatar,String username);


    void deleteUser(Integer uid);

}
