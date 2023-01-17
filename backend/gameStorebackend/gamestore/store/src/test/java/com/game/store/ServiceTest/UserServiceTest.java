package com.game.store.ServiceTest;

import com.game.store.service.IUserService;
import com.game.store.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceTest {
    @Autowired(required = false)
    private IUserService userService;

    //@Test
    public void userRegisterTest(){
        User user = new User();
        user.setUsername("root");
        user.setPassword("12345");
        userService.userRegister(user);
    }
}
