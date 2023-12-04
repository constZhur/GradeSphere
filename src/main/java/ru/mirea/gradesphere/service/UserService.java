package ru.mirea.gradesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.gradesphere.exception.EntityNotFoundException;
import ru.mirea.gradesphere.model.Course;
import ru.mirea.gradesphere.model.Department;
import ru.mirea.gradesphere.model.Group;
import ru.mirea.gradesphere.model.users.Student;
import ru.mirea.gradesphere.model.users.Teacher;
import ru.mirea.gradesphere.model.users.User;
import ru.mirea.gradesphere.repository.CourseRepository;
import ru.mirea.gradesphere.repository.DepartmentRepository;
import ru.mirea.gradesphere.repository.GroupRepository;
import ru.mirea.gradesphere.repository.UserRepository;
import ru.mirea.gradesphere.utils.Role;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, GroupRepository groupRepository, CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public void changeUserStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        user.setEnabled(!user.getEnabled());
        userRepository.save(user);
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public void updateStudentGroup(Long studentId, Long groupId) {
        Student student = (Student) getUserById(studentId);
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));
        student.setGroup(group);
        userRepository.save(student);
    }

    public void updateTeacherDepartment(Long teacherId, Long departmentId) {
        Teacher teacher = (Teacher) getUserById(teacherId);
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departmentId));
        teacher.setDepartment(department);
        userRepository.save(teacher);
    }

    public void addOrUpdateCoursesForUser(Long userId, List<Long> courseIds) {
        User user = getUserById(userId);

        List<Course> courses = courseRepository.findAllById(courseIds);
        if (user instanceof Student) {
            ((Student) user).setCourses(courses);
        } else if (user instanceof Teacher) {
            ((Teacher) user).setCourses(courses);
        }

        userRepository.save(user);
    }

    public void removeCoursesForUser(Long userId, List<Long> courseIds) {
        User user = getUserById(userId);

        if (user instanceof Student) {
            List<Course> coursesToRemove = ((Student) user).getCourses();
            coursesToRemove.removeIf(course -> courseIds.contains(course.getId()));
            ((Student) user).setCourses(coursesToRemove);
        } else if (user instanceof Teacher) {
            List<Course> coursesToRemove = ((Teacher) user).getCourses();
            coursesToRemove.removeIf(course -> courseIds.contains(course.getId()));
            ((Teacher) user).setCourses(coursesToRemove);
        }

        userRepository.save(user);
    }
}
