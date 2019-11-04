/***********************************************************
 * @Description : 博客类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/4 00:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String name;
    private String content;
}
