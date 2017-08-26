package com.myejb22.sssp.controller;

import com.myejb22.sssp.entity.Department;
import com.myejb22.sssp.entity.Employee;
import com.myejb22.sssp.service.DepartmentService;
import com.myejb22.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private DepartmentService departmentService;

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

    @ModelAttribute
    public void getEmployee(@RequestParam(name = "id",required = false) Integer id,Model model) {
        if (null != id) {
            Employee employee = employeeService.get(id);
            employee.setDepartment(null);
            model.addAttribute("employee", employee);
        }
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public String update(Employee employee) {
        employeeService.save(employee);
        return "redirect:/emp/list";
    }

    @RequestMapping(value="/add",method= RequestMethod.GET)
    public String input(Map<String,Object> map){
        map.put("departments", departmentService.getAll());
        map.put("employee", new Employee());
        return "emp/add";
    }

    @RequestMapping(value = "/save")
    public String save(Employee employee) {
        employeeService.save(employee);
        return "redirect:/emp/list";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable(name = "id") Integer id,Model model) {
        Employee employee = employeeService.get(id);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(name = "id") Integer id) {
        employeeService.delete(id);
        return "redirect:/emp/list";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxValidateLastName")
    public String validateLastName(@RequestParam(name = "lastName", required = true) String lastName) {
        return employeeService.validateLastName(lastName);
    }
}
