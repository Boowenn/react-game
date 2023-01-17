package com.game.store.MappersTest;

import com.game.store.entity.District;
import com.game.store.mappers.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class DistrictMapperTest {
    @Autowired(required = false)
    private DistrictMapper districtMapper;

    //@Test
    public void findDistrictByParentTest(){
        List<District> parent = districtMapper.findDistrictByParent("86");
        for (District district : parent) {
            System.out.println(district);
        }
    }
}
