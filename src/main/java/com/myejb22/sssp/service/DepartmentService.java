package com.myejb22.sssp.service;

import com.myejb22.sssp.dao.DepartmentDao;
import com.myejb22.sssp.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/25
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public List<Department> getAll() {
        return departmentDao.getAll();
    }
}
