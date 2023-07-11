package com.ayushi.Task1.repository;

import com.ayushi.Task1.entity.Company;
import com.ayushi.Task1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCompany(Company company);
}
