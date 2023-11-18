package com.alexurba.springdatajpapostgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Integer id;

    @Column(nullable = false)
    private  String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<Address> addressList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workstation_id")
    private Workstation workstation;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "employees_projects", joinColumns = @JoinColumn(name = "id_employee"),  inverseJoinColumns = @JoinColumn(name = "id_project") )
    private List<Project> projectList;


    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> address) {
        this.addressList = address;
    }

    public Workstation getWorkstation() {
        return workstation;
    }

    public void setWorkstation(Workstation workstation) {
        this.workstation = workstation;
    }

}
