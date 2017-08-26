package com.myejb22.sssp.service;

import com.myejb22.sssp.dao.EmployeeDao;
import com.myejb22.sssp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/25
 */
@Transactional(value = "transactionManager")
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
    public String validateLastName(String lastName) {
        Employee employee = employeeDao.findEmployeeByLastName(lastName);
        if (null != employee) {
            return "1";
        }
        return "0";
    }

    /**
     * 会员的保存操作
     *
     * @param employee
     */
    public void save(Employee employee) {
        if (null == employee.getId()) {
            employee.setCreateDate(new Date());
        }
        employeeDao.saveAndFlush(employee);
    }

    /**
     * 根据会员ID来获取会员信息
     * @param id
     * @return
     */
    public Employee get(Integer id) {
        return employeeDao.getOne(id);
    }

    /**
     * 根据会员ID来删除会员信息
     * @param id
     */
    public void delete(Integer id) {
        employeeDao.delete(id);
    }
}
