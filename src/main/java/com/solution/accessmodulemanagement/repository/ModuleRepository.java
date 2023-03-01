package com.solution.accessmodulemanagement.repository;

import com.solution.accessmodulemanagement.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer> {
}
