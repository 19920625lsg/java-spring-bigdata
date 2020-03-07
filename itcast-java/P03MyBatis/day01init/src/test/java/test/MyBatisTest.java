/***********************************************************
 * @Description : MyBatis入门案例
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 1:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package test;

import dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) throws IOException {
        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        // 4.使用Session创建Dao接口的代理对象(代理实现Dao下的所有方法，比如findAll()方法)
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 5.使用代理对象执行方法
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        // 6.释放资源
        session.close();
        in.close();
    }
}
