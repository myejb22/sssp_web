package com.myejb22.sssp.controller;

import com.myejb22.sssp.entity.Employee;
import com.myejb22.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/25
 */
@RequestMapping(value = "/emp")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "pageNo",required = false,defaultValue = "1") String pageNoStr, Model model) {
        int pageNo = 1 - 1;
        try {
            pageNo = Integer.parseInt(pageNoStr) - 1;
        } catch (NumberFormatException e) {}

        PageRequest pageRequest = new PageRequest(pageNo, 5);
        Page<Employee> page = employeeService.findByPage(pageRequest);
        model.addAttribute("page", page);
        return "emp/list";
    }
}
