package com.myejb22.sssp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/24
 */
public class SSSPTest {
    private ApplicationContext ctx = null;
    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void testJpaData() {

    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
}
