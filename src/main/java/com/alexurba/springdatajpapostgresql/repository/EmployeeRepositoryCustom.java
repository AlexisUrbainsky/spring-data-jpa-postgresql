package com.alexurba.springdatajpapostgresql.repository;

import com.alexurba.springdatajpapostgresql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeeRepositoryCustom extends JpaRepository<Employee,Integer> {

}
