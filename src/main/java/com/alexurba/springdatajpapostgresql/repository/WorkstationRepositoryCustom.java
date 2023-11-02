package com.alexurba.springdatajpapostgresql.repository;

import com.alexurba.springdatajpapostgresql.model.Workstation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkstationRepositoryCustom extends CrudRepository<Workstation, Integer> {

}
