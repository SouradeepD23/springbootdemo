package com.souradeep.springbootdemo.repository;

import com.souradeep.springbootdemo.controller.repository.DepartmentRepository;
import com.souradeep.springbootdemo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("ChE")
                .departmentCode("ChE-009")
                .departmentAddress("Udp")
                .build();

        entityManager.persist(department);
    }

    @Test
    @DisplayName("Test findById Happy")
    public void findById_whenValidId_thenReturnDepartment() {
        Department found = departmentRepository.findById(1L).get();

        assertEquals(found.getDepartmentName(), "ChE");
    }
}