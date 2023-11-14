package com.alexurba.springdatajpapostgresql.controller;

import com.alexurba.springdatajpapostgresql.model.Employee;
import com.alexurba.springdatajpapostgresql.model.Project;
import com.alexurba.springdatajpapostgresql.service.EmployeeService;
import com.alexurba.springdatajpapostgresql.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public ProjectService projectService;

    @PostMapping
    public Employee SaveEmployee(@RequestBody Employee employee) {
        try {
            return employeeService.Save(employee);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> GetEmployee(@PathVariable Integer id) {
        return employeeService.FindById(id).map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/assignProject/{idProject}")
    public ResponseEntity<Employee> AssingProjectToEmployee(@RequestBody Employee employee, @PathVariable Integer idProject) {

        Optional<Project> projectOptional = projectService.FindById(idProject);

        if (!projectOptional.isEmpty()) {
            Project project = projectOptional.get();

            if (employee.getProjectList() != null) {
                employee.getProjectList().add(project);
            } else {
                List<Project> list = new ArrayList<Project>();
                list.add(project);
                employee.setProjectList(list);
            }
        }

        return new ResponseEntity<>(employeeService.Save(employee), ACCEPTED);

    }

}
