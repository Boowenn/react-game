package com.game.store.service.Impl;

import com.game.store.entity.District;
import com.game.store.service.IDistrictService;
import com.game.store.mappers.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 处理省市区业务层接口的实现类
 */
@Service
public class IDistrictServiceImpl implements IDistrictService {
    @Autowired(required = false)
    private DistrictMapper districtMapper;

    //根据父代号查询省市区的信息
    @Override
    public List<District> getDistrictByParent(String parent) {
        List<District> districtByParent = districtMapper.findDistrictByParent(parent);
        //无效数据设为null
        for (District district : districtByParent) {
            district.setId(null);
            district.setParent(null);
        }
        return districtByParent;
    }

    //根据code查询省市区的名称
    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
