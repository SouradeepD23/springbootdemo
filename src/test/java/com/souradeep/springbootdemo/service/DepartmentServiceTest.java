package com.souradeep.springbootdemo.service;

import com.souradeep.springbootdemo.entity.Department;
import com.souradeep.springbootdemo.controller.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    /* called before each test is called */
    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Agartala")
                .departmentCode("IT-001")
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Test getDepartmentByName Happy")
    public void getDepartmentByNameTest_whenValidDepartmentName_thenDepartmentFound() {
        String departmentName = "IT";
        Department found = departmentService.getDepartmentByName(departmentName);
        // need to mock the repository method called by the service class

        assertEquals(found.getDepartmentName(), departmentName);
    }
}