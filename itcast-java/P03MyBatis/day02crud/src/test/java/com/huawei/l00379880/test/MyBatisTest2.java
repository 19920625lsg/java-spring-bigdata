/***********************************************************
 * @Description : MyBatis入门案例
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 1:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.test;

import com.huawei.l00379880.dao.IUserDao;
import com.huawei.l00379880.domain.User;
import com.huawei.l00379880.vo.QueryVo;
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
        // 提交事务才能保存到数据库
        session.commit();
        // 6.释放资源
        session.close();
        in.close();
    }

    /**
     * 测试查询所有用户的操作
     */
    @Test
    public void testFindAll() {
        // 5.使用代理对象执行方法
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 根据用户名模糊查询所有用户
     */
    @Test
    public void testFindByUsername() {
        List<User> userList = userDao.findByUsername("%王%");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据id查找用户
     */
    @Test
    public void testFindById() {
        User user = userDao.findById(48);
        System.out.println(user);
    }

    /**
     * 测试获取总记录条数
     */
    @Test
    public void testFindTotal() {
        int total = userDao.findTotal();
        System.out.println("总共有的人数：" + total);
    }

    /**
     * 根据自定义条件模糊查询满足条件的用户
     */
    @Test
    public void testFindByQueryVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> userList = userDao.findByQueryVo(vo);
        for (User u : userList) {
            System.out.println(u);
        }
    }

    /**
     * 测试保存操作，保存后id自动取回来了
     */
    @Test
    public void testSave() {
        // 5.使用代理对象执行方法
        User user = new User("王蕊", new Date(), "女", "上海市浦东新区");
        // 插入前：User{id=null, username='王蕊', birthday=Sun Mar 08 18:57:47 CST 2020, sex='女', address='上海市浦东新区'}
        System.out.println("插入前：" + user);
        userDao.save(user);
        // 插入后：User{id=53, username='王蕊', birthday=Sun Mar 08 18:57:47 CST 2020, sex='女', address='上海市浦东新区'}
        System.out.println("插入后：" + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User(52, "梁山广", new Date(), "男", "上海市浦东新区");
        userDao.update(user);
    }

    /**
     * 测试根据id删除记录
     */
    @Test
    public void testDelete() {
        userDao.delete(46);
    }

}
