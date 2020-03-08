/***********************************************************
 * @Description : 用户的持久层接口
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 0:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.dao;


import com.huawei.l00379880.domain.User;
import com.huawei.l00379880.vo.QueryVo;

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

    /**
     * 根据用户id删除用户
     *
     * @param id 主键
     */
    void delete(Integer id);

    /**
     * 根据主键查询用户
     *
     * @param id 主键
     * @return 查询到的用户
     */
    User findById(Integer id);

    /**
     * 根据用户名模糊查询符合的所有用户
     *
     * @param username 用户名模糊查询词
     * @return 查询到的用户列表
     */
    List<User> findByUsername(String username);

    /**
     * 查询用户总数
     *
     * @return 用户总数
     */
    int findTotal();

    /**
     * 根据自定义查询条件获取结果
     *
     * @param vo 查询参数
     * @return 查询结果
     */
    List<User> findByQueryVo(QueryVo vo);
}
