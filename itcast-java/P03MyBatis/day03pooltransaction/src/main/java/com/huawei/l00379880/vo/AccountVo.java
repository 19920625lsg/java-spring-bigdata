/***********************************************************
 * @Description : 扩展的用户实体类
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 23:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.vo;

import com.huawei.l00379880.domain.Account;

public class AccountVo extends Account {
    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "   AccountVo{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
