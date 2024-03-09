package com.erlong;

import com.erlong.mybatis.dao.UserMapper;
import com.erlong.mybatis.entity.UserEntity;
import com.erlong.springbean.beandemo.SpringBeanDemo;
import com.erlong.springbean.beandemo.Xxh;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootLearn.class)
@Log4j2
public class MainTest {

   // @Autowired
    private UserMapper userMapper;
    @Autowired
    private SqlSessionFactory sessionFactory;
    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    private SpringBeanDemo springBeanDemo;
    @Autowired
    private BeanFactory beanFactory;


    @Test
    @Transactional
    //@Async
    public void test2() {
        UserEntity user = userMapper.selectByUserId(1L);
        System.out.println(user.toString());
        UserEntity u2 = userMapper.selectByUserId(1L);
        System.out.println(u2.toString());
    }



    /**
     * 映射调试上下文
     */
    @Test
    public void testTraceId(){
        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("开始调用服务A，进行业务处理");
        log.info("业务处理完毕，可以释放空间了，避免内存泄露");
        //MDC put 一定要对应一个 remove
        MDC.remove("traceId");
        log.info("traceId {}", MDC.get("traceId"));
    }





    @Test
    public void testBean1(){
        System.out.println("######beanDemo:"+springBeanDemo.getBeanName());
        System.out.println("######beanFactory:"+springBeanDemo);
        SpringBeanDemo demo = (SpringBeanDemo) beanFactory.getBean("springDemo");
        //让bean拥有访问spring容器的能力
        System.out.println("######beanFactory:"+springBeanDemo.getBeanFactory());


    }









    @Test
    public void testBean() throws InterruptedException {
        Xxh xxh = new Xxh();
//        String[] namesForType = applicationContext.getBeanNamesForType(Xxh.class);
//        for (String name : namesForType) {
//            System.out.println("bean名称为===" + name);
//        }
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                Xxh xxh1 =(Xxh)applicationContext.getBean("xxh1");
//                Xxh xxh2 =(Xxh)applicationContext.getBean("xxh2");
//                xxh1.setMobile("134629");
//                System.out.println("xxh1:"+xxh1.toString()+"hashCode:"+xxh1.hashCode());
//                System.out.println("xxh2:"+xxh2.toString()+"hashCode:"+xxh2.hashCode());
//            }).start();
//        }
//        sleep(1000);

//        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(SpringBeanConfiguration.class);
//        Xxh bean2 = applicationContext2.getBean(Xxh.class);
//        System.out.println(bean2+",hashCode:"+bean2.hashCode());
//


// 手动执行close方法
      //  applicationContext2.close();




    }


    @Test
    public void test6() {
        for (int i = 0; i < 100; i++) {
          ///  EhCacheUtil.put("key_" + i, i, UUID.randomUUID());
        }
    }

    @Test
    public void test1() {
        UserEntity user = userMapper.selectByUserId(1L);
        System.out.println(user.toString());
    }






    @Test
    public void test3() {
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //第一次查询，发出sql语句，并将查询的结果放入缓存中
        UserEntity u1 = userMapper.selectByUserId(1L);
        System.out.println(u1);
        //第二步进行了一次更新操作，sqlSession.commit()
    //    u1.setMobile("134629");
        userMapper.updateOne(u1);
        sqlSession.commit();
        //第二次查询，由于是同一个sqlSession.commit(),会清空缓存信息
        //则此次查询也会发出 sql 语句
        UserEntity u2 = userMapper.selectByUserId(1L);
        System.out.println(u2);

        sqlSession.close();
    }

    @Test
    public void testSelectOrderAndUserByOrderId() {
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
    public void testTwoCache1() {
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
