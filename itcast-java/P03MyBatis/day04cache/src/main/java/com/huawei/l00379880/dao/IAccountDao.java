/***********************************************************
 * @Description : 账号的接口
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 22:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.dao;

import com.huawei.l00379880.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 获取所有的账户
     *
     * @return 所有账户的列表
     */
    List<Account> findAll();
}
