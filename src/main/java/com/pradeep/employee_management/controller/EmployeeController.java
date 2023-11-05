package com.pradeep.employee_management.controller;


import com.pradeep.employee_management.model.Employee;
import com.pradeep.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {


    private final EmployeeRepository repository;

    @Autowired
    public EmployeeController(EmployeeRepository repository){
        this.repository=repository;
    }

    @GetMapping({"/showEmployees","/list","/"})
    public ModelAndView showEmployees(){
        ModelAndView mav=new ModelAndView("list-employees");
        List<Employee> list= repository.findAll();
        mav.addObject("employees",list);
        return mav;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav=new ModelAndView("add-employee-form");
        Employee newEmployee=new Employee();
        mav.addObject("employee",newEmployee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        repository.save(employee);
        return "redirect:/list";
    }


    @GetMapping("/showUpdateForm")
    public ModelAndView updateEmployee(@RequestParam Long employeeId){
        ModelAndView mav=new ModelAndView("add-employee-form");
        Employee employee=repository.findById(employeeId).get();
        mav.addObject("employee",employee);
        return mav;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId){
        repository.deleteById(employeeId);
        return "redirect:/list";
    }










}
