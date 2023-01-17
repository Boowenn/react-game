package com.game.store.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @version 1.0.0
 * @desciption 地址类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
    /*
     * aid INT AUTO_INCREMENT COMMENT '收货地址id',
	uid INT COMMENT '归属的用户id',
	name VARCHAR(20) COMMENT '收货人姓名',
	province_name VARCHAR(15) COMMENT '省-名称',
	province_code CHAR(6) COMMENT '省-行政代号',
	city_name VARCHAR(15) COMMENT '市-名称',
	city_code CHAR(6) COMMENT '市-行政代号',
	area_name VARCHAR(15) COMMENT '区-名称',
	area_code CHAR(6) COMMENT '区-行政代号',
	zip CHAR(6) COMMENT '邮政编码',
	address VARCHAR(50) COMMENT '详细地址',
	phone VARCHAR(20) COMMENT '手机',
	tel VARCHAR(20) COMMENT '固话',
	tag VARCHAR(6) COMMENT '标签',
	is_default INT COMMENT '是否默认：0-不默认，1-默认',
	created_user VARCHAR(20) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
     */
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;

    public Address(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
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
