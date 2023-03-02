package com.solution.accessmodulemanagement.service.impl;

import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;
import com.solution.accessmodulemanagement.exception.EmployeeException;
import com.solution.accessmodulemanagement.exception.EmployeeNotFoundException;
import com.solution.accessmodulemanagement.repository.EmployeeRepository;
import com.solution.accessmodulemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        try{
            log.info("adding new employee in database");
            return saveOrUpdate(employee);
        }catch (DataIntegrityViolationException exception){
           throw new EmployeeException("Employee already exists with phone number " + employee.getPhoneNumber() ,HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
        }

    }

    @Override
    public Employee update(Employee employee, Integer id) {
        Employee existingEmployee =  getById(id).orElseThrow(()-> {
            throw new EmployeeNotFoundException("Employee data not found");
        });

        return saveOrUpdate(setEmployeeData(employee, existingEmployee));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> get(Integer id) {
        log.info("Getting an employee from database");
        return employeeRepository.findById(id);
    public Employee get(Integer id) {
        return  getById(id).orElseThrow(()-> {
            throw new EmployeeException("Employee not found",HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseDto delete(Integer id) {
        Employee employee =  getById(id).orElseThrow(()-> {
            throw new EmployeeException("Employee not found",HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        });
        employeeRepository.delete(employee);
        return new ResponseDto("Employee deleted successfully",HttpStatus.OK,HttpStatus.OK.value());
    }

    private Employee saveOrUpdate(Employee employee) {
        return employeeRepository.save(employee);
    }

    private Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }

    private Employee setEmployeeData(Employee employee, Employee existingEmployee) {
        return existingEmployee.builder()
                .id(existingEmployee.getId())
                .name(employee.getName())
                .designation(employee.getDesignation())
                .age(employee.getAge())
                .salary(employee.getSalary())
                .gender(employee.getGender())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .experience(employee.getExperience())
                .module(employee.getModule())
                .build();
    }

}
