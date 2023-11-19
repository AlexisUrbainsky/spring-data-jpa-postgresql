package com.alexurba.springdatajpapostgresql.repository;

import com.alexurba.springdatajpapostgresql.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryCustomTest {

    @Autowired
    private EmployeeRepositoryCustom employeeRepository;

    private Employee employee;

    @Test
    public void create(){
        employee = new Employee();
        employee.setName("alexis");

        Employee saved = employeeRepository.save(employee);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void findById(){
        employee = new Employee();
        employee.setName("alexis");

        Employee saved = employeeRepository.save(employee);
        Optional<Employee> op = employeeRepository.findById(saved.getId());

        Assertions.assertThat(op).isNotEmpty();
        Assertions.assertThat(saved).isEqualTo(op.get());
    }

}