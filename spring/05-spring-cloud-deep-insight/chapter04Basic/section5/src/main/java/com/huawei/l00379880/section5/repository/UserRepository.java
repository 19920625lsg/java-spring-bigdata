/***********************************************************
 * @Description : 数据库操作类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/22 22:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.section5.repository;

import com.huawei.l00379880.section5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
