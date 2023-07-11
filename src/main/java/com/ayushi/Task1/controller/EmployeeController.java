package com.ayushi.Task1.controller;

import com.ayushi.Task1.entity.Employee;
import com.ayushi.Task1.entity.ResponseMessage;
import com.ayushi.Task1.exception.CompanyNotFoundException;
import com.ayushi.Task1.exception.EmployeeNotFoundException;
import com.ayushi.Task1.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/emp/comp/{compId}")
    public Employee saveEmployee(@Valid @PathVariable Long compId, @RequestParam("empData") String empData, @RequestParam("image") MultipartFile image) throws CompanyNotFoundException, IOException {
        return employeeService.saveEmployee(compId, empData, image);
    }

    @GetMapping("/emp/comp/{compId}")
    public List<Employee> getEmployees(@Valid @PathVariable Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        return employeeService.getEmployees(compId);
    }

    @GetMapping(value="/emp/{empId}/comp/{compId}")
    public Employee getEmployeeById(@Valid @PathVariable Long empId, @PathVariable Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        return employeeService.getEmployeeById(empId, compId);
    }

    @GetMapping(value="/empImg/{empId}/comp/{compId}")
    public ResponseEntity<byte[]> getEmployeeImageById(@Valid @PathVariable Long empId, @PathVariable Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        byte[] img = employeeService.getEmployeeImageById(empId, compId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(img);
    }

    @PutMapping("/emp/comp/{compId}")
    public Employee updateEmployee(@Valid @PathVariable Long compId, @RequestParam String empData, @RequestParam MultipartFile image) throws CompanyNotFoundException, IOException, EmployeeNotFoundException {
        return employeeService.updateEmployee(compId, empData, image);
    }

    @DeleteMapping("/emp/{empId}/comp/{compId}")
    public ResponseEntity<ResponseMessage> deleteEmployee(@Valid @PathVariable Long empId, @PathVariable Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        employeeService.deleteEmployee(empId, compId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Boolean.TRUE, "Employee with Id " + empId + " Deleted Successfully"));

    }

}
