package com.alexurba.springdatajpapostgresql.service;

import com.alexurba.springdatajpapostgresql.exception.EmployeeNotFoundException;
import com.alexurba.springdatajpapostgresql.model.Employee;
import com.alexurba.springdatajpapostgresql.repository.EmployeeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepositoryCustom employeeRepository;

    public Optional<Employee> FindById(Integer id){
        return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found")));
    }

    public Employee Save(Employee employee){
        return (Employee) employeeRepository.save(employee);
    }


}
