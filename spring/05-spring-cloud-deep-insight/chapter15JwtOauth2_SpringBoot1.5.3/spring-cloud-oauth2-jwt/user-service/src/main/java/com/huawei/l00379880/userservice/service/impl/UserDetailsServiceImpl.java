/***********************************************************
 * @Description : 系统自带地用户接口实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/5 00:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.userservice.service.impl;

import com.huawei.l00379880.userservice.client.AuthServiceClient;
import com.huawei.l00379880.userservice.dao.UserRepository;
import com.huawei.l00379880.userservice.dto.UserLoginDTO;
import com.huawei.l00379880.userservice.entity.JWT;
import com.huawei.l00379880.userservice.entity.User;
import com.huawei.l00379880.userservice.exception.UserLoginException;
import com.huawei.l00379880.userservice.utils.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthServiceClient client;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User insertUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);
    }

    public UserLoginDTO login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            throw new UserLoginException("error username");
        }
        if (!BPwdEncoderUtil.matches(password, user.getPassword())) {
            throw new UserLoginException("error password");
        }
        // "Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==" can get from postman in "Basic Auth" mode
        JWT jwt = client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==", "password", username, password);
        // if fail to call oauth/token
        if (jwt == null) {
            throw new UserLoginException("error internal");
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;

    }
}
