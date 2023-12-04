package ru.mirea.gradesphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.gradesphere.model.users.User;
import ru.mirea.gradesphere.service.UserService;
import ru.mirea.gradesphere.utils.Role;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}/change_status")
    public void toggleUserStatus(@PathVariable Long id) {
        userService.changeUserStatus(id);
    }

    @PatchMapping("/{studentId}/update_group/{groupId}")
    public void updateStudentGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        userService.updateStudentGroup(studentId, groupId);
    }

    @PatchMapping("/{teacherId}/update_department/{departmentId}")
    public void updateTeacherDepartment(@PathVariable Long teacherId, @PathVariable Long departmentId) {
        userService.updateTeacherDepartment(teacherId, departmentId);
    }

    @PatchMapping("/{userId}/update_courses")
    public void addOrUpdateCoursesForUser(@PathVariable Long userId, @RequestBody List<Long> courseIds) {
        userService.addOrUpdateCoursesForUser(userId, courseIds);
    }

    @PatchMapping("/{userId}/remove_courses")
    public void removeCoursesForUser(@PathVariable Long userId, @RequestBody List<Long> courseIds) {
        userService.removeCoursesForUser(userId, courseIds);
    }

    @GetMapping("/students")
    public List<User> getStudents() {
        return userService.getUsersByRole(Role.STUDENT);
    }

    @GetMapping("/teachers")
    public List<User> getTeachers() {
        return userService.getUsersByRole(Role.TEACHER);
    }

    @GetMapping("/admins")
    public List<User> getAdmins() {
        return userService.getUsersByRole(Role.ADMIN);
    }
}

