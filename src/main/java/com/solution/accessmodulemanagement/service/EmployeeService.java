package com.solution.accessmodulemanagement.service;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto,Integer id);

    List<EmployeeResponseDto> getAll();

    EmployeeResponseDto get();

    void delete(Integer id);

}
