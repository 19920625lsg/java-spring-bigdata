/***********************************************************
 * @Description : 测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 21:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.test;

import com.huawei.l00379880.dao.IUserDao;
import com.huawei.l00379880.domain.User;
import com.huawei.l00379880.vo.QueryVo;
import com.huawei.l00379880.vo.UserVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

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
     * 测试条件查询
     */
    @Test
    public void testFindBuCondition() {
        User u = new User();
        u.setUsername("老王");
        // u.setSex("女"); // 性别是否为空都可以查询到结果
        List<UserVo> userList = userDao.findByCondition(u);
        for (UserVo userVo : userList) {
            System.out.println(userVo);
        }
    }

    /**
     * 测试：根据id列表查询用户列表
     */
    @Test
    public void testFindByIds(){
        QueryVo vo = new QueryVo();
        List<Integer> idList = new ArrayList<>();
        idList.add(41);
        idList.add(42);
        idList.add(52);
        vo.setIdList(idList);

        List<UserVo> userVoList = userDao.findByIds(vo);
        for (UserVo userVo : userVoList) {
            System.out.println(userVo);
        }
    }
}
