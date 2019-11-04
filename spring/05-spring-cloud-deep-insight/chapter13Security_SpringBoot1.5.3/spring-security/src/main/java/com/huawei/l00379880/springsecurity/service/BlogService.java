/***********************************************************
 * @Description : 博客接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/4 00:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springsecurity.service;

import com.huawei.l00379880.springsecurity.entity.Blog;

import java.util.List;

public interface BlogService {
    /**
     * 获取所有的博客组成的列表
     */
    List<Blog> getBlogs();

    /**
     * 根据博客id删除博客
     */
    void deleteBlog(long id);
}
