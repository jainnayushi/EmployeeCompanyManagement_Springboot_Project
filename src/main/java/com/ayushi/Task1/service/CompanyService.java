package com.ayushi.Task1.service;

import com.ayushi.Task1.entity.Company;
import com.ayushi.Task1.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyService {
    public Company saveCompany(Company comp);

    public List<Company> getCompanies() throws CompanyNotFoundException;

    public Company getCompanyById(Long compId) throws CompanyNotFoundException;

    public Company updateCompany(Company comp) throws CompanyNotFoundException;

    public void deleteCompany(Long compId) throws CompanyNotFoundException;

}
