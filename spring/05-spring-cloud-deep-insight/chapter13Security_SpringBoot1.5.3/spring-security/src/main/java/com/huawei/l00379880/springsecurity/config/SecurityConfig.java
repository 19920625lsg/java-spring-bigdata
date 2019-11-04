/***********************************************************
 * @Description : 安全认证的配置类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/3 22:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
/*开启方法级安全*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 方法1：直接添加一个用户的简单方法
        // auth.inMemoryAuthentication().withUser("forezp").password("123456").roles("USER");
        // 方法2：利用InMemoryUserDetailsManager添加多个用户到内存中
        //auth.userDetailsService(userDetailsService());
        // 方法3：自己实现UserDetailService接口并注入到当前类中
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 以"/css/**"开头的资源和"/index"对应的资源不需要权限验证，外接请求可以直接访问这些资源
                .antMatchers("/css/**", "/index").permitAll()
                // 以"/user/**"和"/blog/**"开头的资源需要权限验证，并且只允许权限为USER的角色用户进行访问
                .antMatchers("/user/**", "/blog/**").hasRole("USER")
                .and()
                // 登录网址是"/login"，登录失败的后重定向到"login-error"页面
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                // 异常处理页
                .exceptionHandling().accessDeniedPage("/401")
                .and()
                // 退出成功，返回首页
                .logout().logoutSuccessUrl("/");
    }

// 这个是方法2用到的内存中添加用户，改成方法3数据库此函数要注释掉
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        // 在内存中存放用户信息
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("forezp").password("123456").roles("USER").build());
//        // admin用户同时又USER和ADMIN的权限
//        manager.createUser(User.withUsername("admin").password("123456").roles("ADMIN", "USER").build());
//        return manager;
//    }
}
