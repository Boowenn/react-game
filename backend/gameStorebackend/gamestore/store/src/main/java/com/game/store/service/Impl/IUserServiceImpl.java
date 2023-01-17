package com.game.store.service.Impl;

import com.game.store.service.IUserService;
import com.game.store.service.ex.*;
import com.game.store.entity.User;
import com.game.store.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description 处理用户操作的接口类
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    //处理用户注册
    @Override
    public void userRegister(User user) {
        //首先判断用户名是否在数据库中重复使用
        User res = userMapper.findUserByUsername(user.getUsername());
        //重复的情况下抛出异常
        if(res!=null){
            throw new UsernameDuplicateException("用户名已被注册");
        }
        //加密处理:md5算法
        //串+password+串--->md5，连续加载3次
        //盐值+password+盐值 --->盐值随机字符串
        //记录旧密码
        String oldPass = user.getPassword();
        //使用UUID获取salt
        String salt = UUID.randomUUID().toString().toUpperCase();
        //进行加密操作
        String newPass = getMD5Password(oldPass,salt);
        //对User进行补全
        user.setSalt(salt);
        user.setPassword(newPass);
        //修改逻辑删除判定
        user.setIsDelete(0);
        //补全四个操作字段
        Date currentTime = new Date();
        user.setCreatedTime(currentTime);
        user.setCreatedUser(user.getUsername());
        user.setModifiedTime(currentTime);
        user.setModifiedUser(user.getUsername());

        //调用插入方法 插入用户数据
        Integer row = userMapper.addUser(user);
        //判断插入结果
        if(row!=1){
            throw new InsertException("处理用户注册过程出现未知异常");
        }
    }

    //处理用户登陆
    @Override
    public User userLogin(User user) {
        //用户名
        String username = user.getUsername();
        //密码
        String password = user.getPassword();
        //查询用户是否在数据库中
        User res = userMapper.findUserByUsername(username);
        //判断结果为空或者逻辑删除
        if(res==null||(res.getIsDelete()==1)){
            throw new UserNotFoundException("用户数据不存在");
        }
        //密码校验
        String salt = res.getSalt();
        String databasePass = res.getPassword();
        //获取加密密码
        String md5Password = getMD5Password(password, salt);
        //
        if(!(md5Password.equals(databasePass))){
            throw new PasswordNotMatchException("密码错误");
        }
        //密码正确返回查询结果
        //将查询结果中的uid、username、avatar封装到新的user对象中
        User ret = new User();
        ret.setUid(res.getUid());
        ret.setUsername(res.getUsername());
        ret.setAvatar(res.getAvatar());
        ret.setGender(res.getGender());
        ret.setPhone(res.getPhone());
        ret.setEmail(res.getEmail());
        return ret;
    }
    @Override
    public void changePasswrod(Integer uid, String username, String oldPassword, String newPasswrod) {
        //先查询用户数据是否为空或者逻辑删除
        User res = userMapper.findByUid(uid);
        if(res==null||res.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //数据库密码和旧密码对比
        String dataPassword = res.getPassword();
        String md5Password = getMD5Password(oldPassword, res.getSalt());
        if(!dataPassword.equals(md5Password)){
            throw new PasswordNotMatchException("密码不匹配");
        }
        //插入新密码插入数据库，更新操作人和操作时间
        String newPass = getMD5Password(newPasswrod, res.getSalt());
        Integer row = userMapper.updatePasswordByUid(uid, newPass, username, new Date());
        if(row!=1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }
    @Override
    public User getByUid(Integer uid) {
        User res = userMapper.findByUid(uid);
        if(res==null||res.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User usr = new User();
        //防止重要内容泄漏
        usr.setUsername(res.getUsername());
        usr.setPhone(res.getPhone());
        usr.setEmail(res.getEmail());
        usr.setGender(res.getGender());
        return usr;
    }

//    @Override
//    public List<User> getUser() {
//        //查找数据
//        List<User> res = userMapper.getUser();
//        return res;
//    }


    @Override
    public PageInfo<User> getUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //查找数据
        List<User> res = userMapper.getUser();
        return new PageInfo<>(res);
    }

    /*User对象中的phone/email/gender 手动将uid/username/封装*/
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User res = userMapper.findByUid(uid);
        if(res==null||res.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer row = userMapper.updateInfoByUid(user);
        if(row!=1){
            throw new UpdateException("更新用户数据时产生未知异常");
        }
    }

    /**/
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        //查询当前的用户数据是否存在
        User res = userMapper.findByUid(uid);
        if(res==null||res.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer row = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(row!=1){
            throw new UpdateException("更新用户头像数据产生未知异常");
        }
    }

    @Override
    public void deleteUser(Integer uid) {
        //查询当前的用户数据是否存在
        User res = userMapper.findByUid(uid);
        if(res==null||res.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer row = userMapper.deleteUserByUid(uid);
        if(row!=1){
            throw new UpdateException("删除用户失败");
        }
    }


    /*md5加密*/
    private String getMD5Password(String password,String salt){
        //加密算法
        //加密之后的密匙
        for (int i = 0; i < 3 ; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
