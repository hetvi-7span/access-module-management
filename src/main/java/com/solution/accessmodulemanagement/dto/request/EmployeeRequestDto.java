package com.solution.accessmodulemanagement.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@Data
public class EmployeeRequestDto {
    private String name;
    private String designation;
    private Integer age;
    private Double salary;
    private String gender;
    private String email;
    private String phoneNumber;
    private Double experience;
    private Set<ModuleRequestDto> module;

    public Set<ModuleRequestDto> getModule() {
        return module;
    }

    public void setModule(Set<ModuleRequestDto> module) {
        this.module = module;
    }
}
