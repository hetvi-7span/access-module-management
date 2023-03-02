package com.solution.accessmodulemanagement.service;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;
import com.solution.accessmodulemanagement.entity.ModuleEnum;
import com.solution.accessmodulemanagement.transformer.EmployeeTransformer;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeTransformer employeeTransformer;

    public static Integer  employee_id = null;
    public static String phoneNumber = String.valueOf((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);


    @Test
    void create(){
        Set<ModuleEnum> moduleEnumSet = new HashSet<>();
        moduleEnumSet.add(ModuleEnum.valueOf("DASHBOARD"));
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto().builder()
                .name("Jackson")
                .designation("sr")
                .salary(5000.0)
                .gender("MALE")
                .email("jackson@gmail.com")
                .phoneNumber(phoneNumber)
                .experience(3.0)
                .module(moduleEnumSet)
                .build();
        Employee employee = employeeTransformer.transformEmployeeRequest(employeeRequestDto);
        ResponseEntity<EmployeeResponseDto> responseEntity = ResponseEntity.ok(employeeTransformer.transformEmployeeEntity(employeeService.create(employee), HttpStatus.CREATED,HttpStatus.CREATED.value(),"Employee created successfully"));
        employee_id = Objects.requireNonNull(responseEntity.getBody()).getId();
        Assertions.assertEquals(employeeRequestDto.getName(), responseEntity.getBody().getName());

    }

    @Test
    void EmployeeUpdate(){
        Set<ModuleEnum> moduleEnumSet = new HashSet<>();
        moduleEnumSet.add(ModuleEnum.valueOf("DASHBOARD"));
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto()
                .builder()
                .name("Jackson test")
                .designation("sr")
                .salary(6000.0)
                .age(34)
                .gender("MALE")
                .email("jackson@gmail.com")
                .phoneNumber(phoneNumber)
                .experience(3.0)
                .module(moduleEnumSet)
                .build();
        Employee employee = employeeTransformer.transformEmployeeRequest(employeeRequestDto);

        ResponseEntity<EmployeeResponseDto> responseEntity = ResponseEntity.ok(employeeTransformer.transformEmployeeEntity(employeeService.update(employee,employee_id), HttpStatus.CREATED,HttpStatus.CREATED.value(),"Employee updated successfully"));
        employee_id = Objects.requireNonNull(responseEntity.getBody()).getId();

        employeeService.delete(employee_id);
        Assertions.assertEquals(employeeRequestDto.getName(), responseEntity.getBody().getName());

    }

    @Test
    void  get(){
        ResponseEntity<EmployeeResponseDto> responseEntity = ResponseEntity.ok(employeeTransformer.transformEmployeeEntity(employeeService.get(employee_id),HttpStatus.OK,HttpStatus.OK.value(),"Employee fetch successfully"));
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getName(), "Jackson");
    }

    @Test
    void  getAll(){
        List<Employee> employeeList = employeeService.getAll();

        ResponseEntity<ResponseDto> responseEntity = ResponseEntity.ok(employeeTransformer.transformEmployeeEntityList(employeeList));
        Assertions.assertEquals(responseEntity.getBody().getMessage(), "Data fetched successfully");
    }


}
