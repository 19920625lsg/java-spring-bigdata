/***********************************************************
 * @Description : 用户类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/1 15:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springbootsecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}