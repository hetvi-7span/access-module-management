package com.solution.accessmodulemanagement.service;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto,Integer id);

    List<Employee> getAll();

    Optional<Employee> get(Integer id);

    ResponseDto delete(Integer id);

}
