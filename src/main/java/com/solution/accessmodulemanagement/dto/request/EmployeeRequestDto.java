package com.solution.accessmodulemanagement.dto.request;

import com.solution.accessmodulemanagement.entity.ModuleEnum;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@Data
public class EmployeeRequestDto {

    @NotBlank(message = "Employee name must not be blank")
    @NotNull(message = "Employee name must not be null")
    private String name;
    @NotBlank(message = "Designation must not be blank")
    @NotNull(message = "Designation must not be null")
    private String designation;

    @Positive(message = "Age must be a positive number and greater than zero ")
    @NotNull(message = "Age must not be null")
    private Integer age;

    @NotNull(message = "Age must not be null")
    private Double salary;

    @NotBlank(message = "Gender must not be blank")
    @NotNull(message = "Gender must not be null")
    private String gender;

    @NotBlank(message = "Email address must not be blank")
    @NotNull(message = "Email address must not be null")
    @Email(message = "Email must be in a proper format")
    private String email;

    @NotBlank(message = "Phone number must not be blank")
    @NotNull(message = "Phone number must not be null")
    @Size(min = 10, max = 10, message
            = "Phone number must have 10 digits ")
    private String phoneNumber;
    @PositiveOrZero(message = "Experience be a positive number")
    private Double experience;
    private Set<ModuleEnum> module;

}
