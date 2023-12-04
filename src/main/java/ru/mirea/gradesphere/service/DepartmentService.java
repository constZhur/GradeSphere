package ru.mirea.gradesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.gradesphere.exception.EntityNotFoundException;
import ru.mirea.gradesphere.model.Department;
import ru.mirea.gradesphere.model.Group;
import ru.mirea.gradesphere.model.users.Teacher;
import ru.mirea.gradesphere.model.users.User;
import ru.mirea.gradesphere.repository.DepartmentRepository;
import ru.mirea.gradesphere.repository.GroupRepository;
import ru.mirea.gradesphere.repository.UserRepository;
import ru.mirea.gradesphere.utils.Role;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
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

    public void updateDepartmentHead(Long departmentId, Long teacherId) {
        User user = userRepository.findByIdAndRole(teacherId, Role.TEACHER)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departmentId));

        if (!(user instanceof Teacher)) {
            throw new IllegalArgumentException("User with id " + teacherId + " is not a teacher.");
        }

        Teacher newHead = (Teacher) user;
        department.setHeadOfDepartment(newHead);
        departmentRepository.save(department);
    }

    public void updateGroupDepartment(Long groupId, Long departmentId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        Department newDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departmentId));

        group.setDepartment(newDepartment);
        groupRepository.save(group);
    }
}
