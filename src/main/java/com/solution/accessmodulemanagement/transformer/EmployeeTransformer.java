package com.solution.accessmodulemanagement.transformer;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.dto.response.ModuleResponseDto;
import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;
import com.solution.accessmodulemanagement.entity.Module;
import com.solution.accessmodulemanagement.entity.ModuleEnum;
import com.solution.accessmodulemanagement.exception.EmployeeException;
import com.solution.accessmodulemanagement.repository.ModuleRepository;
import com.solution.accessmodulemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class EmployeeTransformer {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmployeeService employeeService;

    public Employee transformEmployeeRequest(EmployeeRequestDto employeeRequestDto) {
        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);
        if (employeeRequestDto.getModule() != null) {
            Set<ModuleEnum> module = employeeRequestDto.getModule();
            Set<Module> moduleSet = new HashSet<>();
            module.forEach(moduleEnum -> {
                Module existingModule = moduleRepository.findByModuleName(moduleEnum.name());
                moduleSet.add(existingModule);
            });
            employee.setModule(moduleSet);
        }
        return employeeService.create(employee);
    }

    public EmployeeResponseDto transformEmployeeEntity(Employee employee) {
        EmployeeResponseDto employeeResponseDto = entityToDto(employee);
        employeeResponseDto.setHttpStatus(HttpStatus.CREATED);
        employeeResponseDto.setHttpStatusCode(HttpStatus.CREATED.value());
        employeeResponseDto.setMessage("Employee created successfully");
        return employeeResponseDto;
    }


    public ModuleResponseDto transformModuleEntity(Module module) {
        return modelMapper.map(module, ModuleResponseDto.class);
    }

    public ResponseDto transformEmployeeEntityList(List<Employee> employeeList) {
        ResponseDto responseDto = new ResponseDto();
        if (employeeList.size() > 0) {
            List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
            employeeList.forEach(employee -> {
                EmployeeResponseDto employeeResponseDto = entityToDto(employee);
                employeeResponseDtoList.add(employeeResponseDto);
            });
            responseDto.setData(employeeResponseDtoList);
            responseDto.setMessage("Data fetched successfully");
            responseDto.setHttpStatus(HttpStatus.OK);
            responseDto.setHttpStatusCode(HttpStatus.OK.value());
        } else {
            throw new EmployeeException("No data found", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
        log.info("Response generated");
        return responseDto;
    }

    public EmployeeResponseDto entityToDto(Employee employee) {
        EmployeeResponseDto employeeResponseDto = modelMapper.map(employee, EmployeeResponseDto.class);
        if (employee.getModule() != null) {
            Set<ModuleResponseDto> moduleResponseSet = new HashSet<>();
            employee.getModule().forEach(moduleEntity -> moduleResponseSet.add(transformModuleEntity(moduleEntity)));
            employeeResponseDto.setModule(moduleResponseSet);
        }
        return employeeResponseDto;
    }

    public ResponseDto optionalToDto(Employee employeeOptional) {
        ResponseDto responseDto = new ResponseDto();
        if (employeeOptional != null) {
            EmployeeResponseDto employeeResponseDto = entityToDto(employeeOptional);
            responseDto.setData(employeeResponseDto);
            responseDto.setMessage("Data fetched successfully");
            responseDto.setHttpStatus(HttpStatus.OK);
            responseDto.setHttpStatusCode(HttpStatus.OK.value());
        } else {
            throw new EmployeeException("No data found", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
        return responseDto;
    }
}
