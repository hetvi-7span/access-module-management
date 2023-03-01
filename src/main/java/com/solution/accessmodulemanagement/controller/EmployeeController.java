package com.solution.accessmodulemanagement.controller;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired

    EmployeeService employeeService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getAllEmployees(){
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getEmployeeById(@PathVariable int id){
        return null;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody EmployeeRequestDto employeeDto){
        return ResponseEntity.ok(employeeService.create(employeeDto));
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody EmployeeRequestDto employeeDto,@PathVariable("id") Integer id){
        return ResponseEntity.ok(employeeService.update(employeeDto,id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        log.info(String.format("Delete employee data by provided id %s",id));
        employeeService.delete(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

}
