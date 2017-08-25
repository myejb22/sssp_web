package com.myejb22.sssp.service;

import com.myejb22.sssp.dao.EmployeeDao;
import com.myejb22.sssp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/25
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Page<Employee> findByPage(PageRequest page) {
        return employeeDao.findAll(page);
    }

    /**
     * 检查当前用户名是否存在
     *
     * @param lastName
     * @return
     */
    public int validateLastName(String lastName) {
        Employee employee = employeeDao.findEmployeeByLastName(lastName);
        if (null != employee) {
            return 1;
        }
        return 0;
    }
}
