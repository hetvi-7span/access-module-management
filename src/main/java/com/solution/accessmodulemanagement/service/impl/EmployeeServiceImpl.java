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

    /**
     *
     * @param employee
     * @return inserted employee object
     */
    @Override
    public Employee create(Employee employee) {
        try{
            log.info("adding new employee in database");
            return saveOrUpdate(employee);
        }catch (DataIntegrityViolationException exception){
           throw new EmployeeException("Employee already exists with phone number " + employee.getPhoneNumber() ,HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
        }

    }

    /**
     *
     * @param employee
     * @param id
     * @return updates employee object
     */
    @Override
    public Employee update(Employee employee, Integer id) {

        try{
            Employee existingEmployee =  getById(id).orElseThrow(()-> {
                throw new EmployeeNotFoundException("Employee data not found");
            });
            return saveOrUpdate(setEmployeeData(employee, existingEmployee));
        }catch (DataIntegrityViolationException exception){
            throw new EmployeeException("Employee already exists with phone number " + employee.getPhoneNumber() ,HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
        }


    }

    /**
     *
     * @return list of existing employee
     */
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }


    /**
     *
     * @param id
     * @return existing employee object
     */
    public Employee get(Integer id) {
        return  getById(id).orElseThrow(()-> {
            throw new EmployeeException("Employee not found",HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        });
    }

    /**
     *
     * @param id
     * @return response after deleting employee
     */
    @Override
    public ResponseDto delete(Integer id) {
        Employee employee =  getById(id).orElseThrow(()-> {
            throw new EmployeeException("Employee not found",HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        });
        employeeRepository.delete(employee);
        return new ResponseDto("Employee deleted successfully",HttpStatus.OK,HttpStatus.OK.value());
    }

    /**
     * save operation in database
     * @param employee
     * @return inserted employee object
     */
    private Employee saveOrUpdate(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     *
     * @param id
     * @return optional of existing employee
     */
    private Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }

    /**
     *
     * @param employee
     * @param existingEmployee
     * @return employee object
     */
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
