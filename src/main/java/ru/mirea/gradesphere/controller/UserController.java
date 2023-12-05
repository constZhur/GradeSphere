package ru.mirea.gradesphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mirea.gradesphere.dto.UserDto;
import ru.mirea.gradesphere.model.users.User;
import ru.mirea.gradesphere.service.UserService;
import ru.mirea.gradesphere.utils.Role;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);
        return convertToDto(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        User createdUser = userService.createUser(user);
        return convertToDto(createdUser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        User updatedUser = userService.updateUser(id, user);
        return convertToDto(updatedUser);
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
    public List<UserDto> getStudents() {
        List<User> students = userService.getUsersByRole(Role.STUDENT);
        return students.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/teachers")
    public List<UserDto> getTeachers() {
        List<User> teachers = userService.getUsersByRole(Role.TEACHER);
        return teachers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/admins")
    public List<UserDto> getAdmins() {
        List<User> admins = userService.getUsersByRole(Role.ADMIN);
        return admins.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        String[] names = userDto.getFullName().split(" ", 2);
        if (names.length > 0) {
            user.setFirstName(names[0]);
        }
        if (names.length > 1) {
            user.setLastName(names[1]);
        }
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }
}


