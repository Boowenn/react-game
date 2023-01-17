package com.game.store.mappers;

import com.game.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @description User实体类对应的Mapper接口
 */
public interface UserMapper {
    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return Integer 影响的行数
     */
    Integer addUser(User user);

    /**
     * 根据用户查询用户信息
     *
     * @param username 用户名
     * @return 返回User实体类
     */
    User findUserByUsername(String username);

    List<User> getUser();

    /**
     * 根据用户的id查询用户数据
     *
     * @param uid 用户Uid
     * @return 返回用户数据或者null
     */
    User findByUid(Integer uid);

    /**
     * 根据用户Uid修改密码
     *
     * @param uid          用户Uid
     * @param password     用户输入的新密码
     * @param modifiedUser 表示修改的执行者
     * @param modifiedTime 表示修改的时间
     * @return
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * 更新用户信息
     * @param user 用户数据
     * @return 返回影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户的Uid修改头像
     * @param uid   用户Uid
     * @param avatar  头像数据
     * @param modifiedUser 表示修改的执行者
     * @param modifiedTime 表示修改的时间
     * @return
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);

    Integer deleteUserByUid(Integer uid);
}
