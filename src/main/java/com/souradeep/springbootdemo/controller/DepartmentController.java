package com.souradeep.springbootdemo.controller;

import com.souradeep.springbootdemo.entity.Department;
import com.souradeep.springbootdemo.exception.DepartmentNotFoundException;
import com.souradeep.springbootdemo.service.DepartmentService;
import com.souradeep.springbootdemo.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save-department")
    public Department saveDepartment(@Valid @RequestBody Department department) {
//        DepartmentService service = new DepartmentServiceImpl();
//        /* No need to create object manually, we use @Autowired to add IoC and create the object */
        return departmentService.saveDepartment(department);
    }

    @PostMapping("/save-all-departments")
    public List<Department> saveAllDepartment(@Valid @RequestBody List<Department> departments) {
        return departmentService.saveAllDepartment(departments);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.getDepartmentByName(departmentName);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return "Department deleted successfully";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId,
                                       @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);
    }
}
