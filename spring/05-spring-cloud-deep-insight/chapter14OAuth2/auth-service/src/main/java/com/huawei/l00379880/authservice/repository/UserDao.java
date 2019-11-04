package com.huawei.l00379880.authservice.repository;

import com.huawei.l00379880.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
