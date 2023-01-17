package com.game.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description 省市区的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District extends BaseEntity {
    private Integer id;
    private String parent;
    private String code;
    private String name;

    public District(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
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
