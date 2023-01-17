package com.game.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 管理员实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends BaseEntity implements Serializable {
    /*
    uid INT AUTO_INCREMENT COMMENT '管理员id',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    password CHAR(32) NOT NULL COMMENT '密码',
*/
    private Integer aid;//管理员id
    private String username;//用户名
    private String password;//密码
    private String token; // uuid

//    public Admin(String createdToken) {
//        super(createdToken);
//    }
//
//    @Override
//    public void setCreatedToken(String createdToken) {
//        super.setCreatedToken(createdToken);
//    }
}
