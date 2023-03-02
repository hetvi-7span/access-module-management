package com.solution.accessmodulemanagement.transformer;

import com.solution.accessmodulemanagement.dto.request.EmployeeRequestDto;
import com.solution.accessmodulemanagement.dto.response.EmployeeResponseDto;
import com.solution.accessmodulemanagement.dto.response.ModuleResponseDto;
import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import com.solution.accessmodulemanagement.entity.Employee;
import com.solution.accessmodulemanagement.entity.Module;
import com.solution.accessmodulemanagement.entity.ModuleEnum;
import com.solution.accessmodulemanagement.repository.ModuleRepository;
import com.solution.accessmodulemanagement.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
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


    public ModuleResponseDto transformModuleEntity(Module module){
        return modelMapper.map(module,ModuleResponseDto.class);
    }

    public ResponseDto transformEmployeeEntityList(List<Employee> employeeList) {
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        employeeList.forEach(employee ->{
            EmployeeResponseDto employeeResponseDto = entityToDto(employee);
            employeeResponseDtoList.add(employeeResponseDto);
        });
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(employeeResponseDtoList);
        responseDto.setHttpStatus(HttpStatus.OK);
        responseDto.setHttpStatusCode(HttpStatus.OK.value());

        return responseDto;
    }

    private EmployeeResponseDto entityToDto(Employee employee){
        EmployeeResponseDto employeeResponseDto = modelMapper.map(employee, EmployeeResponseDto.class);
        if(employee.getModule() != null){
            Set<ModuleResponseDto> moduleResponseSet = new HashSet<>();
            employee.getModule().forEach(moduleEntity -> moduleResponseSet.add(transformModuleEntity(moduleEntity)));
            employeeResponseDto.setModule(moduleResponseSet);
        }
        return employeeResponseDto;
    }
}
