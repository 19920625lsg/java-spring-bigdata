/***********************************************************
 * @Description : 博客服务实现类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/4 00:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springsecurity.service.impl;

import com.huawei.l00379880.springsecurity.entity.Blog;
import com.huawei.l00379880.springsecurity.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {
    private List<Blog> blogList = new ArrayList<>();

    public BlogServiceImpl() {
        blogList.add(new Blog(1L, "《Java实战》", "Java基础知识讲解"));
        blogList.add(new Blog(2L, "《MySQL从入门到放弃》", "数据库运维与进阶"));
        blogList.add(new Blog(3L, "《PHP是世界上最好的语言》", "Java基础知识讲解"));
        blogList.add(new Blog(4L, "《Python手册》", "人生苦短，我用Python"));
        blogList.add(new Blog(5L, "《C/C++》", "彻底掌握指针"));
    }

    @Override
    public List<Blog> getBlogs() {
        return blogList;
    }

    @Override
    public void deleteBlog(long id) {
        blogList.removeIf(blog -> blog.getId() == id);
    }
}
