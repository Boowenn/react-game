package com.game.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {
    /*
    uid INT AUTO_INCREMENT COMMENT '用户id',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    password CHAR(32) NOT NULL COMMENT '密码',
    salt CHAR(36) COMMENT '盐值',
    phone VARCHAR(20) COMMENT '电话号码',
    email VARCHAR(30) COMMENT '电子邮箱',
    gender INT COMMENT '性别:0-女，1-男',
    avatar VARCHAR(50) COMMENT '头像',
    is_delete INT COMMENT '是否删除：0-未删除，1-已删除',
*/
    private Integer uid;//用户id
    private String username;//用户名
    private String password;//密码
    private String salt;//用于加密密码
    private String phone;//电话号码
    private String email;//电子邮箱
    private Integer gender;//性别:0-女，1-男
    private String avatar;//头像
    private Integer isDelete;//是否删除：0-未删除，1-已删除
}
