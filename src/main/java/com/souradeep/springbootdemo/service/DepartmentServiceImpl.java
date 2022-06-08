package com.souradeep.springbootdemo.service;

import com.souradeep.springbootdemo.entity.Department;
import com.souradeep.springbootdemo.exception.DepartmentNotFoundException;
import com.souradeep.springbootdemo.controller.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> saveAllDepartment(List<Department> departments) {
        return departmentRepository.saveAll(departments);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
//        return departmentRepository.findById(departmentId).get();
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isEmpty()) {
            throw new DepartmentNotFoundException("Department Not Available");
        }

        return department.get();
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depFromDb = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())) {
            depFromDb.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            depFromDb.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())) {
            depFromDb.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(depFromDb);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
//        return departmentRepository.findByDepartmentName(departmentName);
        /* matches exact value - case sensitive */

        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        /* matches value ignoring case - case in-sensitive */
    }
}
