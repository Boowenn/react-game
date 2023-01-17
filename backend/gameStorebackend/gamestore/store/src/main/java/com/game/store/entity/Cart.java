package com.game.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description   购物车商品信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity{
//    cid INT AUTO_INCREMENT COMMENT '购物车数据id',
//    uid INT NOT NULL COMMENT '用户id',
//    pid INT NOT NULL COMMENT '商品id',
//    price BIGINT COMMENT '加入时商品单价',
//    num INT COMMENT '商品数量',
//    created_user VARCHAR(20) COMMENT '创建人',
//    created_time DATETIME COMMENT '创建时间',
//    modified_user VARCHAR(20) COMMENT '修改人',
//    modified_time DATETIME COMMENT '修改时间',
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;

    public Cart(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
    }

    @Override
    public String getCreatedUser() {
        return super.getCreatedUser();
    }

    @Override
    public Date getCreatedTime() {
        return super.getCreatedTime();
    }

    @Override
    public String getModifiedUser() {
        return super.getModifiedUser();
    }

    @Override
    public Date getModifiedTime() {
        return super.getModifiedTime();
    }

    @Override
    public void setCreatedUser(String createdUser) {
        super.setCreatedUser(createdUser);
    }

    @Override
    public void setCreatedTime(Date createdTime) {
        super.setCreatedTime(createdTime);
    }

    @Override
    public void setModifiedUser(String modifiedUser) {
        super.setModifiedUser(modifiedUser);
    }

    @Override
    public void setModifiedTime(Date modifiedTime) {
        super.setModifiedTime(modifiedTime);
    }
}
