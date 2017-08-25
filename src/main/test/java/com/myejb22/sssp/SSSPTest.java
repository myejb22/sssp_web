package com.myejb22.sssp;

import com.myejb22.sssp.dao.DepartmentDao;
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
    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        departmentDao = ctx.getBean("departmentDao", DepartmentDao.class);
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
}
