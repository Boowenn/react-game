package com.game.store.service.Impl;

import com.game.store.mappers.AdminMapper;
import com.game.store.service.ex.UserNotFoundException;
import com.game.store.entity.Admin;
import com.game.store.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description 处理用户操作的接口类
 */
@Service
public class IAdminServiceImpl implements IAdminService {
    @Autowired(required = false)
    private AdminMapper adminMapper;


    //处理用户登陆
    @Override
    public Admin adminLogin(Admin admin) {
        //用户名
        String username = admin.getUsername();
        //密码
        String password = admin.getPassword();
        //查询用户是否在数据库中
        Admin res = adminMapper.findUserByUsername(username);
        System.out.println(res);
        System.out.println(password);
        //判断结果为空或者逻辑删除
        if(res==null){
            throw new UserNotFoundException("账号密码有误");
        }

        System.out.println(res.getPassword() != password);

        Admin result = adminMapper.findUserByUsernameAndPwd(username, password);


        if (result == null) {
            throw new UserNotFoundException("账号密码有误");
        }

        //密码正确返回查询结果
        //将查询结果中的uid、username、avatar封装到新的user对象中
        Admin ret = new Admin();
        ret.setUsername(result.getUsername());
        return ret;
    }

}
