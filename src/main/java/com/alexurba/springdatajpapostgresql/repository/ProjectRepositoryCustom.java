package com.alexurba.springdatajpapostgresql.repository;

import com.alexurba.springdatajpapostgresql.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepositoryCustom extends JpaRepository<Project, Integer> {

}
