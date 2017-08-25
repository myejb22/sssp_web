package com.myejb22.sssp.dao;

import com.myejb22.sssp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/25
 */
public interface DepartmentDao extends JpaRepository<Department,Integer> {


    @QueryHints({@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE,value = "true")})
    @Query(value = "FROM Department d")
    List<Department> getAll();
}
