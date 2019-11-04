/***********************************************************
 * @Description : 用户服务，用到Security内部的类较多
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/1 15:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springbootsecurity.service.impl;

import com.huawei.l00379880.springbootsecurity.entity.Role;
import com.huawei.l00379880.springbootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.huawei.l00379880.springbootsecurity.entity.User user = userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getUsername(), user.getPassword(), authorityList);
    }
}
