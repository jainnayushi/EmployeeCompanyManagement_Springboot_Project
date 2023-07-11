package com.ayushi.Task1.service;

import com.ayushi.Task1.entity.Company;
import com.ayushi.Task1.exception.CompanyNotFoundException;
import com.ayushi.Task1.exception.CustomException;
import com.ayushi.Task1.repository.CompanyRepository;
import com.ayushi.Task1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public Company saveCompany(Company comp) {
        return companyRepo.save(comp);
    }

    @Override
    public List<Company> getCompanies() throws CompanyNotFoundException {
        if (companyRepo.findAll().isEmpty()) {
            throw new CompanyNotFoundException("No Company found");
        }
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyById(Long compId) throws CompanyNotFoundException {
        if (companyRepo.findById(compId).isEmpty()) {
            throw new CompanyNotFoundException("Company with id " + compId + " not available");
        }
        return companyRepo.findById(compId).get();
    }

    @Override
    public Company updateCompany(Company comp) throws CompanyNotFoundException {
        if (comp.getCompId()==null) {
            throw new CustomException("Company Id is required");
        }
        Long compId = comp.getCompId();
        Company compDB = companyRepo.findById(compId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + compId + " not available"));
        compDB.setCompName(comp.getCompName());
        return companyRepo.save(compDB);
    }

    @Override
    public void deleteCompany(Long compId) throws CompanyNotFoundException {
        Company companyDB = companyRepo.findById(compId)
                .orElseThrow(()-> new CompanyNotFoundException("Company with id " + compId + " not available to be deleted"));

        if(!employeeRepo.findByCompany(companyDB).isEmpty()){
            throw new CustomException("Deletion Failed : Since, Employees associated with Company of Id-" + compId + " exists");
        }
        companyRepo.deleteById(compId);

    }
}