package com.solution.accessmodulemanagement.controller;

import com.solution.accessmodulemanagement.dto.request.EmployeeDto;
import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {



    @GetMapping("/")
    public ResponseEntity<ResponseDto> getAllEmployees(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEmployeeById(@PathVariable int id){
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> addEmployee(@RequestBody EmployeeDto employeeDto){
        return null;
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody EmployeeDto employeeDto){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable int id){
        return null;
    }





}
