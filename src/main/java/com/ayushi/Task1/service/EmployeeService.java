package com.ayushi.Task1.service;

import com.ayushi.Task1.entity.Employee;
import com.ayushi.Task1.exception.CompanyNotFoundException;
import com.ayushi.Task1.exception.EmployeeNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    public Employee saveEmployee(Long compId, String empData, MultipartFile image) throws CompanyNotFoundException, IOException;

    public List<Employee> getEmployees(Long compId) throws EmployeeNotFoundException, CompanyNotFoundException;

    public Employee getEmployeeById(Long empId, Long compId) throws EmployeeNotFoundException, CompanyNotFoundException;

    public byte[] getEmployeeImageById(Long empId, Long compId) throws EmployeeNotFoundException, CompanyNotFoundException;

//    public Employee updateEmployee(Long empId, Long compId, String empData, MultipartFile image) throws CompanyNotFoundException, IOException, EmployeeNotFoundException;

    public Employee updateEmployee(Long compId, String empData, MultipartFile image) throws CompanyNotFoundException, IOException, EmployeeNotFoundException;

    public void deleteEmployee(Long empId, Long compId) throws EmployeeNotFoundException, CompanyNotFoundException;

}
