package com.alexurba.springdatajpapostgresql.service;

import com.alexurba.springdatajpapostgresql.exception.EmployeeNotFoundException;
import com.alexurba.springdatajpapostgresql.model.Employee;
import com.alexurba.springdatajpapostgresql.repository.EmployeeRepositoryCustom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepositoryCustom employeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder().name("alexis").build();
    }

    @Test
    void findById() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        Assertions.assertThat(employeeService.FindById(employee.getId())).isNotNull();
    }

    @Test
    void findById_EmployeeNotFoundException() throws EmployeeNotFoundException {
        when(employeeRepository.findById(anyInt())).thenThrow( new EmployeeNotFoundException("Employee Not Found"));
        assertThrows(EmployeeNotFoundException.class, () ->employeeService.FindById(anyInt()));
    }

    @Test
    void save() {
    }

    @Test
    void getAll() {
    }
}