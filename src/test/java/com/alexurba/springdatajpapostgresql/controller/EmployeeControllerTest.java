package com.alexurba.springdatajpapostgresql.controller;

import com.alexurba.springdatajpapostgresql.exception.EmployeeNotFoundException;
import com.alexurba.springdatajpapostgresql.model.Employee;
import com.alexurba.springdatajpapostgresql.repository.EmployeeRepositoryCustom;
import com.alexurba.springdatajpapostgresql.service.EmployeeService;
import com.alexurba.springdatajpapostgresql.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    EmployeeRepositoryCustom employeeRepository;
    @MockBean
    ProjectService projectService;
    Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder().id(1).name("alexis").build();
    }

    @Test
    public void getAllEmployees_success() throws Exception {
        List<Employee>  listEmp = new ArrayList<Employee>(Arrays.asList(employee));

        Mockito.when(employeeService.GetAll()).thenReturn(listEmp);

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("alexis"))).andReturn();

        Assertions.assertEquals(HttpStatus.FOUND.value(), result.getResponse().getStatus());
    }


    @Test
    public void FindByIdEmployee_success() throws Exception {

        Mockito.when(employeeService.FindById(employee.getId())).thenReturn(Optional.of(employee));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(employee.getName())));
    }

    @Test
    public void FindByIdEmployee_NotFoundException() throws Exception {
        Mockito.when(employeeService.FindById(1)).thenThrow(new EmployeeNotFoundException("Employee Not Found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isNotFound()
                        ).andReturn();

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }


    @Test
    public void createEmployee_success() throws Exception{

        Mockito.when(employeeService.Save(employee)).thenReturn(employee);

    }


}
