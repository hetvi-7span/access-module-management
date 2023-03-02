package com.solution.accessmodulemanagement.service.impl;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;
import com.solution.accessmodulemanagement.exception.EmployeeException;
import com.solution.accessmodulemanagement.exception.EmployeeNotFoundException;
import com.solution.accessmodulemanagement.repository.EmployeeRepository;
import com.solution.accessmodulemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmployeeRepository employeeRepository;



    @Override
    public Employee create(Employee employee) {
        try{
            return employeeRepository.save(employee);
        }catch (DataIntegrityViolationException exception){
           throw new EmployeeException("Employee already exists with phone number 348938498394",HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
        }

    }

    @Override
    public EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto, Integer id) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(()-> {
            throw new EmployeeNotFoundException("Employee data not found");
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
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public EmployeeResponseDto get() {
        return null;
    }

    @Override
    public void delete(Integer id) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(()-> {
            throw new EmployeeException("Employee not found",HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        });
        employeeRepository.delete(employee);
    }

}
