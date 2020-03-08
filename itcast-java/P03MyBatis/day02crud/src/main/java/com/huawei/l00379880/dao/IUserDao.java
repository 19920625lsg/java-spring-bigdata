/***********************************************************
 * @Description : 用户的持久层接口
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 0:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.dao;


import com.huawei.l00379880.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     *
     * @return 所有的用户信息
     */
    List<User> findAll();


    /**
     * 保存用户
     *
     * @param user 要插入数据库的用户信息
     */
    void save(User user);

    /**
     * 更新用户
     *
     * @param user 要更新的用户
     */
    void update(User user);
}
