package ru.mirea.gradesphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/change_status")
    public void toggleUserStatus(@PathVariable Long id) {
        userService.changeUserStatus(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{studentId}/update_group/{groupId}")
    public void updateStudentGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        userService.updateStudentGroup(studentId, groupId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{teacherId}/update_department/{departmentId}")
    public void updateTeacherDepartment(@PathVariable Long teacherId, @PathVariable Long departmentId) {
        userService.updateTeacherDepartment(teacherId, departmentId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{userId}/update_courses")
    public void addOrUpdateCoursesForUser(@PathVariable Long userId, @RequestBody List<Long> courseIds) {
        userService.addOrUpdateCoursesForUser(userId, courseIds);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{userId}/remove_courses")
    public void removeCoursesForUser(@PathVariable Long userId, @RequestBody List<Long> courseIds) {
        userService.removeCoursesForUser(userId, courseIds);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/students")
    public List<User> getStudents() {
        return userService.getUsersByRole(Role.STUDENT);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/teachers")
    public List<User> getTeachers() {
        return userService.getUsersByRole(Role.TEACHER);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/admins")
    public List<User> getAdmins() {
        return userService.getUsersByRole(Role.ADMIN);
    }
}

