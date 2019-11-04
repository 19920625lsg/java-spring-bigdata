package com.huawei.l00379880.userservice.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by fangzhipeng on 2017/5/31.
 */
public class BPwdEncoderUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 用BCryptPasswordEncoder
     */
    public static String BCryptPassword(String password) {
        return ENCODER.encode(password);
    }

    /**
     * verify password and password having been encrypted
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }


}
