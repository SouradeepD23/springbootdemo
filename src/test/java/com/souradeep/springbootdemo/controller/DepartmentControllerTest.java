package com.souradeep.springbootdemo.controller;

import com.souradeep.springbootdemo.entity.Department;
import com.souradeep.springbootdemo.exception.DepartmentNotFoundException;
import com.souradeep.springbootdemo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentCode("IT-001")
                .departmentAddress("Agartala")
                .build();
    }

    @Test
    @DisplayName("Save Department Test")
    void saveDepartmentTest() throws Exception {
        Department inputDeparment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-001")
                .departmentAddress("Agartala")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDeparment))
                .thenReturn(department);

        mockMvc.perform(post("/save-department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"IT\",\n" +
                        "    \"departmentAddress\":\"Agartala\",\n" +
                        "    \"departmentCode\":\"IT-001\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get Department by ID Test")
    void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));

    }
}