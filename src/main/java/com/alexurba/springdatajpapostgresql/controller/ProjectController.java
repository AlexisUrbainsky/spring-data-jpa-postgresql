package com.alexurba.springdatajpapostgresql.controller;

import com.alexurba.springdatajpapostgresql.model.Project;
import com.alexurba.springdatajpapostgresql.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> Save(@RequestBody Project project){
        return  new ResponseEntity<>(projectService.Save(project), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> FindById(@PathVariable Integer id){
        return projectService.FindById(id).map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
