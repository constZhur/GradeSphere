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
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(name = "id") Long id,
                           @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/change_status")
    public void toggleUserStatus(@PathVariable(name = "id") Long id) {
        userService.changeUserStatus(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{studentId}/update_group/{groupId}")
    public void updateStudentGroup(@PathVariable(name = "studentId") Long studentId,
                                   @PathVariable(name = "groupId") Long groupId) {
        userService.updateStudentGroup(studentId, groupId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{teacherId}/update_department/{departmentId}")
    public void updateTeacherDepartment(@PathVariable(name = "teacherId") Long teacherId,
                                        @PathVariable(name = "departmentId") Long departmentId) {
        userService.updateTeacherDepartment(teacherId, departmentId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{userId}/update_courses")
    public void addOrUpdateCoursesForUser(@PathVariable(name = "userId") Long userId,
                                          @RequestBody List<Long> courseIds) {
        userService.addOrUpdateCoursesForUser(userId, courseIds);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{userId}/remove_courses")
    public void removeCoursesForUser(@PathVariable(name = "userId") Long userId,
                                     @RequestBody List<Long> courseIds) {
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

