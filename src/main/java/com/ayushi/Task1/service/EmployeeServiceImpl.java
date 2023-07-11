package com.ayushi.Task1.service;

import com.ayushi.Task1.entity.Company;
import com.ayushi.Task1.entity.Employee;
import com.ayushi.Task1.exception.CompanyNotFoundException;
import com.ayushi.Task1.exception.CustomException;
import com.ayushi.Task1.exception.EmployeeNotFoundException;
import com.ayushi.Task1.repository.CompanyRepository;
import com.ayushi.Task1.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private CompanyRepository companyRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Employee saveEmployee(Long compId, String empData, MultipartFile image) throws CompanyNotFoundException, IOException {
        Company companyDB = companyRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not available"));
        Employee emp = objectMapper.readValue(empData, Employee.class);
        if (image.getContentType() != null && !image.getContentType().matches("(?i)(image/png|image/jpeg)")) {
            throw new CustomException("Only PNG/JPEG Content type allowed");
        }
        emp.setEmpImage(image.isEmpty() ? null : image.getBytes());
        emp.setCompany(companyDB);
        return employeeRepo.save(emp);
    }

    @Override
    public List<Employee> getEmployees(Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        Company companyDB = companyRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not available"));
        List<Employee> employees = employeeRepo.findByCompany(companyDB);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employee in Company with Id-" + compId + " found");
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long empId, Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        Company companyDB = companyRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not available"));
        Employee employeeDB = employeeRepo.findById(empId)
                .orElseThrow(()->new EmployeeNotFoundException("Employee of Id " + empId + " not available"));
        if (!employeeDB.getCompany().equals(companyDB)) {
            throw new EmployeeNotFoundException("Company of id-" + compId + " does not associate with Employee of id-" + employeeDB.getEmpId());
        }
        return employeeDB;
    }

    @Override
    public byte[] getEmployeeImageById(Long empId, Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        Company companyDB = companyRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not available"));
        Employee employeeDB = employeeRepo.findById(empId)
                .orElseThrow(()->new EmployeeNotFoundException("Employee of Id " + empId + " not available"));
        if (!employeeDB.getCompany().equals(companyDB)) {
            throw new EmployeeNotFoundException("Company of id-" + compId + " does not associate with Employee of id-" + employeeDB.getEmpId());
        }
        return employeeDB.getEmpImage();
    }

    @Override
    public Employee updateEmployee(Long compId, String empData, MultipartFile image) throws CompanyNotFoundException, IOException, EmployeeNotFoundException {
        Employee emp = objectMapper.readValue(empData, Employee.class);
        if (emp.getEmpId()==null) {
            throw new CustomException("Employee Id is required");
        }
        Company companyDB = companyRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not available"));
        Employee employeeDB = employeeRepo.findById(emp.getEmpId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + emp.getEmpId() + " not available"));

        if (!employeeDB.getCompany().equals(companyDB)) {
            throw new EmployeeNotFoundException("Company of id-" + compId + " does not associate with Employee of id-" + emp.getEmpId());
        }
        if (image.getContentType() != null && !image.getContentType().matches("(?i)(image/png|image/jpeg)")) {
            throw new CustomException("Only PNG/JPEG Content type allowed");
        }
        employeeDB.setEmpName(emp.getEmpName());
        employeeDB.setEmpJoiningDate(emp.getEmpJoiningDate());
        employeeDB.setEmpImage(image.isEmpty() ? null : image.getBytes());
        employeeDB.setCompany(companyDB);

        return employeeRepo.save(employeeDB);
    }

    @Override
    public void deleteEmployee(Long empId, Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        Company companyDB = companyRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not available"));
        Employee employeeDB = employeeRepo.findById(empId)
                .orElseThrow(()->new EmployeeNotFoundException("Employee of Id " + empId + " not available"));
        if (!employeeDB.getCompany().equals(companyDB)) {
            throw new EmployeeNotFoundException("Company of id-" + compId + " does not associate with Employee of id-" + employeeDB.getEmpId());
        }
        employeeRepo.deleteById(empId);
    }
}
