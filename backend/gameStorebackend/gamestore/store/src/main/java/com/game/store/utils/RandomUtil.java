package com.game.store.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    // 全部字符
    public static final String allCharacter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // 字母字符
    public static final String letterCharacter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // 数字字符
    public static final String numberCharacter = "0123456789";

    /**
     * 随机生成36位字符
     *
     * @return
     */
    public static String UUID36() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}