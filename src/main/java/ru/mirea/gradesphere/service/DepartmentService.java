package ru.mirea.gradesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.gradesphere.exception.EntityNotFoundException;
import ru.mirea.gradesphere.model.Department;
import ru.mirea.gradesphere.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        department.setId(id);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }
}
