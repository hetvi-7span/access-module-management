package com.solution.accessmodulemanagement;

import com.solution.accessmodulemanagement.entity.Module;
import com.solution.accessmodulemanagement.repository.ModuleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AccessModuleManagementApplication implements CommandLineRunner {


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	ModuleRepository moduleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccessModuleManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Set<String> moduleSet = new HashSet<>();
//		moduleSet.add("Dashboard");
//		moduleSet.add("Report");
//		moduleSet.add("Library");
//
//		 moduleSet.forEach(item -> {
//			 Module module = new Module();
//			 module.setModuleName(item);
//			 moduleRepository.save(module);
//		 });
	}
}
