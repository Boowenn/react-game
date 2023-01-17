package com.game.store.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 所有实体类的基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
    /*
    created_user VARCHAR(20) COMMENT '日志-创建人',
    created_time DATETIME COMMENT '日志-创建时间',
    modified_user VARCHAR(20) COMMENT '日志-最后修改执行人',
    modified_time DATETIME COMMENT '日志-最后修改时间',
    */
    //抽取出四个公共字段
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
//    private String createdToken;
//
//    public BaseEntity(String createdToken) {
//
//    }
}
