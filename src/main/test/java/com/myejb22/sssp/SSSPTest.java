package com.myejb22.sssp;

import com.myejb22.sssp.dao.DepartmentRepository;
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
    private DepartmentRepository departmentRepository;
    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        departmentRepository = ctx.getBean("departmentRepository", DepartmentRepository.class);
    }

    @Test
    public void testFindAll() {
        List<Department> departmentList = departmentRepository.getAll();
        departmentList = departmentRepository.getAll();
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
