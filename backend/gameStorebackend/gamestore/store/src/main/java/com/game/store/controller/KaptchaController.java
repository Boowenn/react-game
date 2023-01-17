package com.game.store.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @version 1.0
 * @description: kaptcha的控制层,kaptcha调用
 */
@Slf4j
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Producer producer;

    @GetMapping("/kaptcha-image")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //禁止server缓存
        response.setDateHeader("Expires", 0);
        //设置标准的http/1.1 no-cache headers
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 设置IE扩展 HTTP/1.1 no-cache headers (use addHeader)
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 设置标准 HTTP/1.0 不缓存图片
        response.setHeader("Pragma", "no-cache");
        // 返回一个 jpeg 图片，默认是text/html(输出文档的MIMI类型)
        response.setContentType("image/jpeg");
        // 为图片创建文本
        String capText = producer.createText();
        // 输出验证码
        log.info("******************当前验证码为：{}******************", capText);
        // 将验证码存于session中
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // 创建带有文本的图片
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // 向页面输出验证码
        ImageIO.write(bi, "jpg", out);
        try {
            // 清空缓存区
            out.flush();
        } finally {
            // 关闭输出流
            out.close();
        }
    }
}
