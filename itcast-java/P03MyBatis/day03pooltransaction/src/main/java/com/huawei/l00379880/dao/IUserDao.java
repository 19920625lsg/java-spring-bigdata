/***********************************************************
 * @Description : 用户的持久层接口
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 0:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.dao;


import com.huawei.l00379880.domain.User;
import com.huawei.l00379880.vo.QueryVo;
import com.huawei.l00379880.vo.UserVo;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     */
    List<User> findAll();

    /**
     * 根据传入的user的部分属性查询符合条件地用户列表
     *
     * @param user 不完整的用户对象，部分属性可能为空
     * @return 用户列表
     */
    List<UserVo> findByCondition(User user);

    /**
     * 根据id列表查询指定的对象列表
     *
     * @param vo 自封装的查询对象
     * @return 用户列表
     */
    List<UserVo> findByIds(QueryVo vo);
}
