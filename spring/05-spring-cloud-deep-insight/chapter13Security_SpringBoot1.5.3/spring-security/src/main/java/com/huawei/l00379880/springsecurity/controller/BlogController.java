/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/4 00:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springsecurity.controller;

import com.huawei.l00379880.springsecurity.entity.Blog;
import com.huawei.l00379880.springsecurity.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blog")
    public ModelAndView list(Model model) {

        List<Blog> list = blogService.getBlogs();
        model.addAttribute("blogList", list);
        return new ModelAndView("blog/list", "blogModel", model);
    }

    /*方法级别的注释，只有ADMIN用户才可以执行到这个方法*/
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/blog/{id}/deletion")
    public ModelAndView delete(@PathVariable("id") Long id, Model model) {
        blogService.deleteBlog(id);
        model.addAttribute("blogList", blogService.getBlogs());
        return new ModelAndView("blog/list", "blogModel", model);
    }
}
