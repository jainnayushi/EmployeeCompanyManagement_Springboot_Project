//package com.ayushi.Task1.service;
//
//import com.ayushi.Task1.entity.Company;
//import com.ayushi.Task1.entity.Employee;
//import com.ayushi.Task1.exception.CompanyNotFoundException;
//import com.ayushi.Task1.repository.CompanyRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class CompanyServiceImplTest {
//    @Autowired
//    private CompanyService companyService;
//
//    @MockBean
//    private CompanyRepository companyRepo;
//    Company comp1 = Company.builder().compId(27L).compName("XYZ Updated Company").build();
//    Company comp2 = Company.builder().compId(28L).compName("PQR Company").build();
//    List<Company> companies = new ArrayList<>();
//    Employee emp1 = Employee.builder()
//            .empId(1L)
//            .empName("Emp 1")
//            .empJoiningDate(LocalDate.ofEpochDay(2023 - 06 - 11))
//            .company(comp1)
//            .build();
//
//    @BeforeEach
//    void setUp() {
//        companies.add(comp1);
//        companies.add(comp2);
//
//        Mockito.when(companyRepo.findById(27L)).thenReturn(Optional.of(comp1));
//        Mockito.when(companyRepo.findAll()).thenReturn(companies);
//        Mockito.when(companyRepo.save(comp1)).thenReturn(comp1);
//    }
//
//    @Test
//    void addCompany() {
//        assertEquals(comp1.getCompId(), companyService.saveCompany(comp1).getCompId());
//        assertEquals(comp1.getCompName(), companyService.saveCompany(comp1).getCompName());
//    }
//
//    @Test
//    void getCompanies() throws CompanyNotFoundException {
//        assertEquals(2, companyService.getCompanies().size());
//    }
//
//    @Test
//    void getCompanyById() throws CompanyNotFoundException {
//        Long compId = 27L;
//        String compName = "XYZ Updated Company";
//        Company comp = companyService.getCompanyById(compId);
//        assertEquals(27L, comp.getCompId());
//        assertEquals(compName, comp.getCompName());
//    }
//
//    @Test
//    void updateCompany() throws CompanyNotFoundException {
//        Company comp1Updated = Company.builder().compId(27L).compName("Comp Updated").build();
//        assertEquals(comp1Updated.getCompName(), companyService.updateCompany(27L, comp1Updated).getCompName());
//        assertEquals("Comp Updated", companyService.updateCompany(27L, comp1Updated).getCompName());
//    }
//
//    @Test
////    @Disabled
//    public void deleteCompany() throws CompanyNotFoundException {
//        doNothing().when(companyRepo).deleteById(27L);
//        companyService.deleteCompany(27L);
//        verify(companyRepo,times(1)).deleteById(27L);
//
//    }
//}