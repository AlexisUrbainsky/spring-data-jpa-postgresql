package com.alexurba.springdatajpapostgresql;


import com.alexurba.springdatajpapostgresql.controller.EmployeeController;
import com.alexurba.springdatajpapostgresql.model.Employee;
import com.alexurba.springdatajpapostgresql.repository.EmployeeRepositoryCustom;
import com.alexurba.springdatajpapostgresql.service.EmployeeService;
import com.alexurba.springdatajpapostgresql.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    Employee emp1 = new Employee(1, "Alexis");

    @Test
    public void getAllEmployees_success() throws Exception {
        List<Employee>  listEmp = new ArrayList<Employee>(Arrays.asList(emp1));

        Mockito.when(employeeService.GetAll()).thenReturn(listEmp);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Alexis")));
    }


}
