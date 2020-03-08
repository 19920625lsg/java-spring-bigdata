/***********************************************************
 * @Description : MyBatis入门案例
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 1:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.test;

import com.huawei.l00379880.dao.IUserDao;
import com.huawei.l00379880.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest2 {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    /**
     * 在测试方法执行前执行一些初始化的工作
     */
    @Before
    public void init() throws IOException {
        // 1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产SqlSession对象
        session = factory.openSession();
        // 4.使用Session创建Dao接口的代理对象(代理实现Dao下的所有方法，比如findAll()方法)
        userDao = session.getMapper(IUserDao.class);
    }

    /**
     * 测试方法执行后执行，一般用于清理资源
     */
    @After
    public void destroy() throws IOException {
        // 6.释放资源
        session.close();
        in.close();
    }

    /**
     * 测试查询所有用户的操作
     */
    @Test
    public void testFindAll() throws IOException {
        // 5.使用代理对象执行方法
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() throws IOException {
        // 5.使用代理对象执行方法
        User user = new User("梁山广", new Date(), "m", "上海市浦东新区");
        userDao.save(user);
        // 提交事务才能保存到数据库
        session.commit();
    }
}
