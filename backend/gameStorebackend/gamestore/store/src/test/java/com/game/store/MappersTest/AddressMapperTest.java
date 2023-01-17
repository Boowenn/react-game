package com.game.store.MappersTest;

import com.game.store.entity.Address;
import com.game.store.mappers.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class AddressMapperTest {
    @Autowired(required = false)
    private AddressMapper addressMapper;

   // @Test
    public void findByUidTest(){
        List<Address> res = addressMapper.findByUid(1);
        for (Address a : res) {
            System.out.println(a);
        }
    }
}
