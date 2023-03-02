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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "employee_module_details",
            joinColumns =
                    {@JoinColumn(name = "emp_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "module_id", referencedColumnName = "id")})
    Set<Module> module;
}
