package com.game.store.service.Impl;

import com.game.store.entity.Address;
import com.game.store.service.IAddressService;
import com.game.store.service.ex.*;
import com.game.store.mappers.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description 收货地址业务层的实现类
 */
@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired(required = false)
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addAddress(Integer uid, String username, Address address) {
        //先判断用户地址的条数
        Integer count = addressMapper.userAddressCount(uid);
        //判断是否超过20条记录
        if(count>=maxCount){
            throw new AddressCountLimitException("用户地址已经到达上限，请删除部分地址");
        }
        //判断当前地址是否为0
        if(count==0){
            address.setIsDefault(1);
        }else{
            address.setIsDefault(0);
        }
        //补全四项日志
        Date currentTime = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(currentTime);
        address.setModifiedUser(username);
        address.setModifiedTime(currentTime);
        //插入
        Integer row = addressMapper.addAddress(address);
        //判断插入结果
        if(row!=1){
            throw new InsertException("插入时产生未知异常");
        }
    }

    /**
     * 按照用户uid查询用户的收货地址集合
     * @param uid   用户的uid
     * @return 返回用户的收货地址的集合
     */
    @Override
    public List<Address> getAddressByUid(Integer uid) {
        //获取结果
        List<Address> res = addressMapper.findByUid(uid);
        //由于网页端只需要地址类型，收件人姓名，详细地址，联系电话，所以对信息进行部分过滤
        for (Address address : res) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return res;
    }

    @Override
    public void setDefault(Integer uid, Integer aid, String username) {
        Address res = addressMapper.findByAid(aid);
        if(res==null){
            throw new AddressNotFoundException("用户收货地址不存在");
        }
        //先将所有的地址设为非默认地址
        Integer row = addressMapper.updateNoneDefault(uid);
        if(row<1){
            throw new UpdateException("将所有地址设置为非默认地址时出现异常");
        }
        //按照aid将收货地址设置为默认地址
        Integer updateRow = addressMapper.updateDefault(aid, username, new Date());
        if(updateRow==0){
            throw new UpdateException("设置默认地址时出现异常");
        }
    }

    @Override
    public void deleteAddressByAid(Integer aid,Integer uid,String username) {
        //需要先判断用户有多少条地址
        Integer count = addressMapper.userAddressCount(uid);
        //判断用户收货地址是否存在
        Address byAid = addressMapper.findByAid(aid);
        if(byAid==null)throw new AddressNotFoundException("用户收货地址不存在");
        //如果地址多条 而且当前要删除的地址为默认地址
        if(count>1&&byAid.getIsDefault()==1){
            //先删除当前地址
            //按照用户aid删除
            Integer row = addressMapper.DeleteAddressByAid(aid);
            //判断是否出现异常
            if(row!=1)throw new DeleteException("删除用户收货地址时出现未知异常");
            //查询所有的地址，由于查询是按照创造时间排序的
            List<Address> byUid = addressMapper.findByUid(uid);
            //设置默认地址
            addressMapper.updateDefault(byUid.get(0).getAid(),username,new Date());
            return;
        }
        //如果只有一条，就不用判断是否需要再次设置默认地址
        //按照用户aid删除
        Integer row = addressMapper.DeleteAddressByAid(aid);
        //判断是否出现异常
        if(row!=1)throw new DeleteException("删除用户收货地址时出现未知异常");
    }

    @Override
    public Address findAddressByAid(Integer aid) {
        Address data = addressMapper.findByAid(aid);
        if(data==null)throw new AddressNotFoundException("用户收货地址数据不存在");
        return data;
    }
}
