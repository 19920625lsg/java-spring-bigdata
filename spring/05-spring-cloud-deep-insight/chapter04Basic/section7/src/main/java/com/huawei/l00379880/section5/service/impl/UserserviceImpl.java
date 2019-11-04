/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/22 22:38
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.section5.service.impl;

import com.huawei.l00379880.section5.entity.User;
import com.huawei.l00379880.section5.repository.UserRepository;
import com.huawei.l00379880.section5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserserviceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
