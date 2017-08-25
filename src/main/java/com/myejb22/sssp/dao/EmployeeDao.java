package com.myejb22.sssp.dao;

import com.myejb22.sssp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/25
 */
public interface EmployeeDao extends JpaRepository<Employee,Integer> {
}
