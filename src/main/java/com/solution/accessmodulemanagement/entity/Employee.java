package com.solution.accessmodulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "employee_details" ,uniqueConstraints = { @UniqueConstraint(columnNames = { "phone_number" }) })
@ToString
public class Employee extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String designation;
    private Integer age;
    private Double salary;
    private String gender;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Double experience;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "employee_module_details")
    Set<Module> module;
}
