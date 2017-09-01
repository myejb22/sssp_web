package com.myejb22.sssp;

import com.myejb22.sssp.dao.DepartmentDao;
import com.myejb22.sssp.dao.RedisDao;
import com.myejb22.sssp.entity.Department;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/24
 */
public class SSSPTest {
    private ApplicationContext ctx = null;
    private DepartmentDao departmentDao;
    private RedisDao redisDao;
    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        departmentDao = ctx.getBean("departmentDao", DepartmentDao.class);
        redisDao = ctx.getBean("redisDao", RedisDao.class);
    }

    @Test
    public void testFindAll() {
        List<Department> departmentList = departmentDao.getAll();
        departmentList = departmentDao.getAll();
    }

    @Test
    public void testJpaData() {

    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testRedis() {
        System.out.println(redisDao);
    }

    @Test
    public void testRedisDataWrite() {
        redisDao.writeString("k1","v1");
        redisDao.writeString("k2","v2");
        redisDao.writeString("k3","v3");
        redisDao.writeString("k4","v4");
    }

    @Test
    public void testRedisCommand() {
        System.out.println(redisDao.readStringByKey("k1"));
        //System.out.println(redisDao.del("k2"));
        //redisDao.appendStr("k1", "来吧来吧");
        /*System.out.println(redisDao.strLength("k1"));
        System.out.println(redisDao.incr("incr"));
        System.out.println(redisDao.decr("incr"));
        System.out.println(redisDao.incrby("incr",20));
        System.out.println(redisDao.decrby("incr",10));
        redisDao.setRang("k1", "我是JAVA程序员", 3);
        System.out.println(redisDao.getRang("k1", 0, -1));
        redisDao.setEx("k3", 20, "hello world");
        System.out.println(redisDao.readStringByKey("k3"));
        System.out.println(redisDao.setNX("k4","sddd"));*/
       // redisDao.mSet(new String[]{"k2","k3","k6"},new String[]{"v2","v3","v6"});
       /* List<String> list = redisDao.mGet("k2", "k3", "k6");
        System.out.println(list);*/
        System.out.println(redisDao.getSet("k7", "v7"));
    }
}
