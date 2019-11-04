/***********************************************************
 * @Description : 数据库操作类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/1 15:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springbootsecurity.repository;

import com.huawei.l00379880.springbootsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
