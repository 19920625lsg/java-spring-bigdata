/***********************************************************
 * @Description : 用户的持久层接口
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 0:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package dao;

import domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     */
    List<User> findAll();
}
