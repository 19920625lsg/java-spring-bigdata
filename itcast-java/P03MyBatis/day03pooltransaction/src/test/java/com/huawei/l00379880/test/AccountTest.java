/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 22:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.test;

import com.huawei.l00379880.dao.IAccountDao;
import com.huawei.l00379880.domain.Account;
import com.huawei.l00379880.domain.User;
import com.huawei.l00379880.vo.AccountVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSession session;
    private IAccountDao accountDao;

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
        accountDao = session.getMapper(IAccountDao.class);
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
        List<Account> accountList = accountDao.findAll();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindAllInfo(){
        List<AccountVo> accountVoList = accountDao.findAllInfo();
        for (AccountVo accountVo : accountVoList) {
            System.out.println(accountVo);
        }
    }
}
