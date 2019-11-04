/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/22 22:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.section5.service;

import com.huawei.l00379880.section5.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findUserByUsername(String username);
}
