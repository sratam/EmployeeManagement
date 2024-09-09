package com.birdi.EmployeeBackend.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;

    private String name;
    private String email;
    private String role;
    private String status;
    private Integer salary;

    @Column(name = "hire_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    public Employee(){
        super();
        this.hireDate = new Date();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
