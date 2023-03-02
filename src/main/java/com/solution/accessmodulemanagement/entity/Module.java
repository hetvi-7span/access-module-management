package com.solution.accessmodulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "module_details",uniqueConstraints = { @UniqueConstraint(columnNames = { "module_name" }) })
public class Module extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "module_name")
    private String moduleName;
}
