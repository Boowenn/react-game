package com.game.store.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @version 1.0
 * @description: kaptcha配置类
 */
@Slf4j
@Configuration
public class KaptchaConfig {
    //kaptcha
    @Bean
    public DefaultKaptcha getKaptcheCode() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // 创建properties
        Properties properties = new Properties();
        //是否有边框 NO
        properties.setProperty("kaptcha.border", "no");
        //字体颜色 black
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //图片宽度 100
        properties.setProperty("kaptcha.image.width", "100");
        //图片高度 36
        properties.setProperty("kaptcha.image.height", "36");
        //字体大小 30px
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //图片样式 阴影
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        //session key = code
        properties.setProperty("kaptcha.session.key", "code");
        //干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        //背景渐变颜色 开始颜色
        properties.setProperty("kaptcha.background.clear.from", "232,240,254");
        //背景渐变颜色 结束颜色
        properties.setProperty("kaptcha.background.clear.to", "232,240,254");
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "彩云,宋体,楷体,微软雅黑");
        //设置参数
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
