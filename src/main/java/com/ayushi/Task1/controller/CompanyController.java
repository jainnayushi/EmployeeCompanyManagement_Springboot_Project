package com.ayushi.Task1.controller;

import com.ayushi.Task1.entity.Company;
import com.ayushi.Task1.entity.ResponseMessage;
import com.ayushi.Task1.exception.CompanyNotFoundException;
import com.ayushi.Task1.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/comp")
    public Company saveCompany(@Valid @RequestBody Company comp){
        System.err.println(comp);
        return companyService.saveCompany(comp);
    }

    @GetMapping("/comp")
    public List<Company> getCompanies() throws CompanyNotFoundException {
        return companyService.getCompanies();
    }

    @GetMapping("/comp/{compId}")
    public Company getCompanyById(@Valid @PathVariable("compId") Long compId) throws CompanyNotFoundException {
        return companyService.getCompanyById(compId);
    }

    @PutMapping("/comp")
    public Company updateCompany(@RequestBody Company comp) throws CompanyNotFoundException {
        return companyService.updateCompany(comp);
    }

    @DeleteMapping("/comp/{compId}")
    public ResponseEntity<ResponseMessage> deleteCompany(@Valid @PathVariable("compId") Long compId) throws CompanyNotFoundException {
        companyService.deleteCompany(compId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Boolean.TRUE, "Company with Id " + compId + " Deleted Successfully"));
    }
}
