package com.solution.accessmodulemanagement.service.impl;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;
import com.solution.accessmodulemanagement.exception.EmployeeNotFoundException;
import com.solution.accessmodulemanagement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.solution.accessmodulemanagement.service.EmployeeService;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired

    EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.save(modelMapper.map(employeeRequestDto,Employee.class));
        EmployeeResponseDto employeeResponseDto = modelMapper.map(employee,EmployeeResponseDto.class);
        employeeResponseDto.setHttpStatus(HttpStatus.CREATED);
        employeeResponseDto.setHttpStatusCode(HttpStatus.CREATED.value());
        employeeResponseDto.setMessage("Employee created successfully");
        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto, Integer id) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(()-> {
            return new EmployeeNotFoundException("Employee data not found");
        });
//        Employee employee1 = employee.builder()
//                .name(employeeRequestDto.getName())
//                .designation(employeeRequestDto.getDesignation())
//                .age(employeeRequestDto.getAge())
//                .salary(employeeRequestDto.getSalary())
//                .gender(employeeRequestDto.getGender())
//                .email(employeeRequestDto.getEmail())
//                .phoneNumber(employee.getPhoneNumber())
//                .experience(employeeRequestDto.getExperience())
//                .build();
        EmployeeResponseDto employeeResponseDto = modelMapper.map(employeeRepository.save(employee),EmployeeResponseDto.class);
        employeeResponseDto.setHttpStatus(HttpStatus.OK);
        employeeResponseDto.setHttpStatusCode(HttpStatus.OK.value());
        employeeResponseDto.setMessage("Employee data updated successfully");
        return employeeResponseDto;
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        return null;
    }

    @Override
    public EmployeeResponseDto get() {
        return null;
    }

    @Override
    public void delete(Integer id) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(()-> {
            return new EmployeeNotFoundException("Employee data not found");
        });
        employeeRepository.delete(employee);
    }
}
