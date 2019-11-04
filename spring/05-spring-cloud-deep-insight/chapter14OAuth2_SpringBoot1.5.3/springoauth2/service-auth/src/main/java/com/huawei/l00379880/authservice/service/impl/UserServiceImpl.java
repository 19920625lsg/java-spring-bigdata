/***********************************************************
 * @Description : 用户服务实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/5 00:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.authservice.service.impl;

import com.huawei.l00379880.authservice.dao.UserRepository;
import com.huawei.l00379880.authservice.entity.User;
import com.huawei.l00379880.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user) {
        User existing = userRepository.findByUsername(user.getUsername());
        Assert.isNull(existing, "user already exists: " + user.getUsername());
        String hash = ENCODER.encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
    }
}
