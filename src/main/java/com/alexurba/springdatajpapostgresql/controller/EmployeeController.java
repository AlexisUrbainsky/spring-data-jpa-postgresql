package com.alexurba.springdatajpapostgresql.controller;

import com.alexurba.springdatajpapostgresql.model.Employee;
import com.alexurba.springdatajpapostgresql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @PostMapping
    public Employee SaveEmployee(@RequestBody Employee employee){
        return employeeService.Save(employee);
    }

    @GetMapping("/{id}")
    public Optional<Employee> GetEmployee(@PathVariable Integer id){
        return employeeService.FindById(id);
    }


}
