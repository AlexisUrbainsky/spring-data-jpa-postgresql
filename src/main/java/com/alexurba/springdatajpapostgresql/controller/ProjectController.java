package com.alexurba.springdatajpapostgresql.controller;

import com.alexurba.springdatajpapostgresql.model.Project;
import com.alexurba.springdatajpapostgresql.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Project Save(@RequestBody Project project){
        return projectService.Save(project);
    }

    @GetMapping("/{id}")
    public Optional<Project> FindById(@PathVariable Integer id){
        return projectService.FindById(id);
    }

}
