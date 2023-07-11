//package com.ayushi.Task1.service;
//
//import com.ayushi.Task1.entity.Company;
//import com.ayushi.Task1.entity.Employee;
//import com.ayushi.Task1.exception.CompanyNotFoundException;
//import com.ayushi.Task1.exception.EmployeeNotFoundException;
//import com.ayushi.Task1.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
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
//class EmployeeServiceImplTest {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @MockBean
//    private EmployeeRepository employeeRepo;
//
//    Company comp = Company.builder().compId(27L).compName("XYZ Updated Company").build();
//    Employee emp1 = Employee.builder().empId(1L).empName("Emp 1").empJoiningDate(LocalDate.ofEpochDay(2023 - 06 - 11)).company(comp).build();
//    Employee emp2 = Employee.builder().empId(2L).empName("Emp 2").empJoiningDate(LocalDate.ofEpochDay(2023 - 06 - 11)).company(comp).build();
//    List<Employee> employees = new ArrayList<Employee>();
//
//    @BeforeEach
//    void setUp() {
//        employees.add(emp1);
//        employees.add(emp2);
//
//        when(employeeRepo.findById(1L)).thenReturn(Optional.of(emp1));
//        when(employeeRepo.findAll()).thenReturn(employees);
//        when(employeeRepo.save(emp1)).thenReturn(emp1);
//        Mockito.doNothing().when(employeeRepo).deleteById(2L);
//    }
//
//    @Test
//    void addEmployee() {
//        assertEquals(emp1.getEmpId(), employeeService.addEmployee(emp1).getEmpId());
//        assertEquals(emp1.getEmpName(), employeeService.addEmployee(emp1).getEmpName());
//        assertEquals(emp1.getEmpJoiningDate(), employeeService.addEmployee(emp1).getEmpJoiningDate());
//        assertEquals(emp1.getCompany(), employeeService.addEmployee(emp1).getCompany());
//    }
//
//    @Test
//    void getEmployees() {
//        assertEquals(2, employeeService.getEmployees().size());
//    }
//
//    @Test
//    public void getEmployeeById() throws EmployeeNotFoundException {
//        Long empId = 1L;
//        String empName = "Emp 1";
//        Employee e = employeeService.getEmployeeById(empId);
//        assertEquals(empId, e.getEmpId());
//        assertEquals(empName, e.getEmpName());
//    }
//
//    @Test
//    void updateEmployee() {
//        Employee emp1Updated = Employee.builder().empId(1L).empName("Emp 1 Updated").empJoiningDate(LocalDate.ofEpochDay(2023 - 06 - 11)).company(comp).build();
//        assertEquals(emp1Updated.getEmpName(), employeeService.updateEmployee(1L, emp1Updated).getEmpName());
//        assertNotEquals(emp2.getEmpName(), employeeService.updateEmployee(1L, emp1Updated).getEmpName());
//        assertEquals("Emp 1 Updated", employeeService.updateEmployee(1L, emp1Updated).getEmpName());
//        assertNotEquals("Emp 1", employeeService.updateEmployee(1L, emp1Updated).getEmpName());
//    }
//
//    @Test
////    @Disabled
//    void deleteEmployee() throws EmployeeNotFoundException {
//        Long id = 1L;
//        employeeService.deleteEmployee(id);
//        // Verify that deleteById was called on the repository with the correct id
//        Mockito.verify(employeeRepo, Mockito.times(1)).deleteById(id);
//    }
//
//
//}
