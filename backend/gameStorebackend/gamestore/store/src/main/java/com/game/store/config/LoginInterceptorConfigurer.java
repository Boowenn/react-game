package com.game.store.config;

import com.game.store.interceptor.LgoinInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration//加载当前的拦截器并进行注册
//处理器拦截器的注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    //将自定义的拦截器进行注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //自定义一个拦截器对象
        HandlerInterceptor interceptor = new LgoinInterceptor();
        //配置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/user/**");
        patterns.add("/kaptcha/**");
        patterns.add("/address/**");
        patterns.add("/cart/**");
        patterns.add("/district/**");
        patterns.add("/product/**");
        patterns.add("/admin/**");
        patterns.add("/file/**");
        patterns.add("/avatar/**");
        patterns.add("/newImg/**");
        patterns.add("/favorite/**");
        //向注册器对象添加拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//要拦截的Url是什么
                .excludePathPatterns(patterns);
    }
}
