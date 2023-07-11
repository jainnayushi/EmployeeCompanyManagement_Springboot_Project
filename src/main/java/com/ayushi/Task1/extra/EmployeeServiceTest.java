//package com.ayushi.Task1.service;
//
//import com.ayushi.Task1.entity.Employee;
//import com.ayushi.Task1.exception.CompanyNotFoundException;
//import com.ayushi.Task1.exception.CustomException;
//import com.ayushi.Task1.exception.EmployeeNotFoundException;
//import com.ayushi.Task1.repository.CompanyRepository;
//import com.ayushi.Task1.repository.EmployeeRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//class EmployeeServiceTest {
//    @Mock
//    private EmployeeRepository employeeRepository;
//    @Mock
//    private CompanyRepository companyRepository;
//    @Mock
//    private ObjectMapper objectMapper;
//    @InjectMocks
//    private EmployeeServiceImpl employeeService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    // Existing test cases...
//
//    @Test
//    void saveEmployee_WithInvalidCompanyId_ShouldThrowCompanyNotFoundException() throws CompanyNotFoundException, IOException {
//        // Arrange
//        Long compId = 1L;
////        Company comp = Company.builder().CompId(1L).CompName("Company A").build();
//        String employeeData = "{\"empName\":\"Ayushi Jain\",\"empJoiningDate\":\"2022-01-01\"}}";
//        MultipartFile image = new MockMultipartFile("image", new byte[]{1, 2, 3});
//
//        when(companyRepository.findById(compId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(CompanyNotFoundException.class, () -> employeeService.saveEmployee(compId, employeeData, image));
//        verify(employeeRepository, never()).save(any());
//    }
//
//    @Test
//    void saveEmployee_WithMissingEmployeeData_ShouldThrowCustomException() throws CompanyNotFoundException, IOException {
//        // Arrange
//        Long companyId = 1L;
////        Company comp = Company.builder().CompId(1L).CompName("Company A").build();
//        String invalidEmployeeData = "{\"empName\":\"Ayushi Jain\"}}";
//        MultipartFile image = new MockMultipartFile("image", new byte[]{1, 2, 3});
//
//        // Act & Assert
//        assertThrows(CustomException.class, () -> employeeService.saveEmployee(companyId, invalidEmployeeData, image));
//        verify(employeeRepository, never()).save(any());
//    }
//
//    @Test
//    void saveEmployee_WithInvalidImageData_ShouldThrowCustomException() throws CompanyNotFoundException, IOException {
//        // Arrange
//        Long companyId = 1L;
//        String employeeData = "{\"empName\":\"Ayushi Jain\",\"empJoiningDate\":\"2022-01-01\",\"company\":{\"compId\":1,\"compName\":\"Company A\"}}";
//        MultipartFile invalidImage = new MockMultipartFile("image", (byte[]) null);
//
//        // Act & Assert
//        assertThrows(CustomException.class, () -> employeeService.saveEmployee(companyId, employeeData, invalidImage));
//        verify(employeeRepository, never()).save(any());
//    }
//
//
//    @Test
//    void saveEmployee_WithInvalidEmployeeData_ShouldThrowIOException() throws CompanyNotFoundException, IOException {
//        // Arrange
//        Long companyId = 1L;
//        String invalidEmployeeData = "{\"empName\":\"Ayushi Jain\",\"empJoiningDate\":\"2022-01-01\",\"company\":{\"compId\":1,\"compName\":\"Company A\"}}";
//        MultipartFile image = new MockMultipartFile("image", new byte[]{1, 2, 3});
//
//        when(objectMapper.readValue(eq(invalidEmployeeData), eq(Employee.class))).thenThrow(IOException.class);
//
//        // Act & Assert
//        assertThrows(IOException.class, () -> employeeService.saveEmployee(companyId, invalidEmployeeData, image));
//        verify(employeeRepository, never()).save(any());
//    }
//
//    @Test
//    void deleteEmployee_WithInvalidId_ShouldThrowEmployeeNotFoundException() throws EmployeeNotFoundException {
//        // Arrange
//        Long invalidEmployeeId = 99L;
//        when(employeeRepository.existsById(invalidEmployeeId)).thenReturn(false);
//
//        // Act & Assert
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(invalidEmployeeId));
//        verify(employeeRepository, never()).deleteById(anyLong());
//    }
//
//    // Additional negative test cases...
//
//    @Test
//    void saveEmployee_WithNullCompanyId_ShouldThrowCompanyNotFoundException() throws CompanyNotFoundException, IOException {
//        // Arrange
//        Long companyId = null;
//        String employeeData = "{\"empName\":\"Ayushi Jain\",\"empJoiningDate\":\"2022-01-01\",\"company\":{\"compId\":1,\"compName\":\"Company A\"}}";
//        MultipartFile image = new MockMultipartFile("image", new byte[]{1, 2, 3});
//
//        // Act & Assert
//        assertThrows(CompanyNotFoundException.class, () -> employeeService.saveEmployee(companyId, employeeData, image));
//        verify(employeeRepository, never()).save(any());
//    }
//
//    @Test
//    void deleteEmployee_WithNullId_ShouldThrowEmployeeNotFoundException() throws EmployeeNotFoundException {
//        // Arrange
//        Long nullEmployeeId = null;
//
//        // Act & Assert
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(nullEmployeeId));
//        verify(employeeRepository, never()).deleteById(anyLong());
//    }
//
//    @Test
//    void saveEmployee_WithNullEmployeeData_ShouldThrowCustomException() throws CompanyNotFoundException, IOException {
//        // Arrange
//        Long companyId = 1L;
//        String nullEmployeeData = null;
//        MultipartFile image = new MockMultipartFile("image", new byte[]{1, 2, 3});
//
//        // Act & Assert
//        assertThrows(CustomException.class, () -> employeeService.saveEmployee(companyId, nullEmployeeData, image));
//        verify(employeeRepository, never()).save(any());
//    }
//
//    @Test
//    void saveEmployee_WithNullImageData_ShouldThrowCustomException() throws CompanyNotFoundException, IOException {
//        // Arrange
//        Long companyId = 1L;
//        String employeeData = "{\"empName\":\"Ayushi Jain\",\"empJoiningDate\":\"2022-01-01\",\"company\":{\"compId\":1,\"compName\":\"Company A\"}}";
//        MultipartFile nullImage = null;
//
//        // Act & Assert
//        assertThrows(CustomException.class, () -> employeeService.saveEmployee(companyId, employeeData, nullImage));
//        verify(employeeRepository, never()).save(any());
//    }
//}
