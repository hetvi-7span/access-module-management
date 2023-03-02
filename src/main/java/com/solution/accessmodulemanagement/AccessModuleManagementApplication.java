package com.solution.accessmodulemanagement;

import com.solution.accessmodulemanagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccessModuleManagementApplication implements CommandLineRunner {

	@Autowired
	ModuleService moduleService;

	public static void main(String[] args) {
		SpringApplication.run(AccessModuleManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		moduleService.createModule();
	}
}
