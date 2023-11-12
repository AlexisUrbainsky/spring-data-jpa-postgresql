package com.alexurba.springdatajpapostgresql.service;

import com.alexurba.springdatajpapostgresql.model.Project;
import com.alexurba.springdatajpapostgresql.repository.ProjectRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepositoryCustom projectRepositoryCustom;

    public Project Save(Project project){
        return projectRepositoryCustom.save(project);
    }

    public Optional<Project> FindById(Integer id){
        return projectRepositoryCustom.findById(id);
    }

}
