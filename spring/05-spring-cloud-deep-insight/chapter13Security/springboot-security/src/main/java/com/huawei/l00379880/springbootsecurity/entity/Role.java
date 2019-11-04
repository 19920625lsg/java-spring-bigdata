/***********************************************************
 * @Description : 角色类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/1 15:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springbootsecurity.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
