package com.solution.accessmodulemanagement.controller;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;
import com.solution.accessmodulemanagement.service.EmployeeService;
import com.solution.accessmodulemanagement.transformer.EmployeeTransformer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeTransformer employeeTransformer;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAll();
        return ResponseEntity.ok(employeeTransformer.transformEmployeeEntityList(employeeList));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.get(id);
        return ResponseEntity.ok(employeeTransformer.optionalToDto(employee.get()));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponseDto> addEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto) {
        Employee employee = employeeTransformer.transformEmployeeRequest(employeeDto);
        return ResponseEntity.ok(employeeTransformer.transformEmployeeEntity(employee));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody EmployeeRequestDto employeeDto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.update(employeeDto, id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable int id) {
        log.info(String.format("Delete employee data by provided id %s", id));
        return ResponseEntity.ok(employeeService.delete(id));

    }

}
