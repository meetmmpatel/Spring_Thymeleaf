package com.spring.thymeleaf.Spring_Thymeleaf.repository;

import com.spring.thymeleaf.Spring_Thymeleaf.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
