package com.game.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description 商品的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{
    private Integer id;
//    private Integer categoryId ;
//    private String itemType;
    private String title;
//    private String sellPoint;
    private Long price;
    private Integer num;
//    private String image;
//    private Integer status;
//    private String priority;
    private String imageUrl;

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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected boolean canEqual(Object other) {
        return super.canEqual(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}