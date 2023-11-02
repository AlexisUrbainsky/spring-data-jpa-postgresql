package com.alexurba.springdatajpapostgresql.repository;

import com.alexurba.springdatajpapostgresql.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeeRepositoryCustom extends CrudRepository <Employee,Integer>{

}
