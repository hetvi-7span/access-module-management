package com.solution.accessmodulemanagement.service.impl;

import com.solution.accessmodulemanagement.entity.Module;
import com.solution.accessmodulemanagement.entity.ModuleEnum;
import com.solution.accessmodulemanagement.repository.ModuleRepository;
import com.solution.accessmodulemanagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;
    @Override
    public void createModule() {

        for (ModuleEnum moduleEnum: ModuleEnum.values()) {
            Module isModuleExists = moduleRepository.findByModuleName(moduleEnum.name());
            if(isModuleExists == null){
                Module module = new Module();
                module.setModuleName(moduleEnum.name());
                moduleRepository.save(module);
            }
        }
    }

}
