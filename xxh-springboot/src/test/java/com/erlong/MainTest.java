package com.erlong;
import com.erlong.mybatis.dao.UserMapper;
import com.erlong.mybatis.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootLearn.class)
public class MainTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Test
    public void test1(){
        UserEntity user = userMapper.selectByUserId(1L);
        System.out.println(user.toString());
    }



    @Test
    public void test2(){
        UserEntity user = userMapper.selectByUserId(1L);
        System.out.println(user.toString());
        UserEntity u2 = userMapper.selectByUserId(1L);
        System.out.println(u2.toString());
    }
    @Test
    public void test3(){
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //第一次查询，发出sql语句，并将查询的结果放入缓存中
        UserEntity u1 = userMapper.selectByUserId(1L);
        System.out.println(u1);
        //第二步进行了一次更新操作，sqlSession.commit()
        u1.setMobile("134629");
        userMapper.updateOne(u1);
        sqlSession.commit();
        //第二次查询，由于是同一个sqlSession.commit(),会清空缓存信息
        //则此次查询也会发出 sql 语句
        UserEntity u2 = userMapper.selectByUserId(1L);
        System.out.println(u2);

        sqlSession.close();
    }
    @Test
    public void testSelectOrderAndUserByOrderId(){
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession();
        String statement = "one.to.one.mapper.OrdersMapper.selectOrderAndUserByOrderID";
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //第一次查询，发出sql语句，并将查询的结果放入缓存中
        UserEntity u1 = userMapper.selectByUserId(1l);
        sqlSession.close();
        System.out.println(u1);
        //第二次查询，由于是同一个sqlSession,会在缓存中查找查询结果
        //如果有，则直接从缓存中取出来，不和数据库进行交互
        UserEntity u2 = userMapper.selectByUserId(1L);
        System.out.println(u2);
        sqlSession.close();
    }


    @Test
    public void testTwoCache1(){
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();

        String statement = "com.ys.twocache.UserMapper.selectUserByUserId";
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        //第一次查询，发出sql语句，并将查询的结果放入缓存中
        UserEntity u1 = userMapper1.selectByUserId(1L);
        System.out.println(u1);
        sqlSession1.close();//第一次查询完后关闭sqlSession

        //第二次查询，即使sqlSession1已经关闭了，这次查询依然不发出sql语句
        UserEntity u2 = userMapper2.selectByUserId(1l);
        System.out.println(u2);
        sqlSession2.close();
    }



}
