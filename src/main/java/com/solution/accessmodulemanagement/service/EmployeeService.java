package com.solution.accessmodulemanagement.service;

import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee create(Employee employee);

    Employee update(Employee employee,Integer id);

    List<Employee> getAll();

    Employee get(Integer id);

    ResponseDto delete(Integer id);

}
