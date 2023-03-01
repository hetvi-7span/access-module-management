package com.solution.accessmodulemanagement.dto.response;

import com.solution.accessmodulemanagement.entity.Module;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
public class EmployeeResponseDto extends ResponseDto{
    private Integer id;
    private String employeeCode;
    private String name;
    private String designation;
    private Integer age;
    private Double salary;
    private String gender;
    private String email;
    private String phoneNumber;
    private Double experience;
    private Set<Module> module;
}
