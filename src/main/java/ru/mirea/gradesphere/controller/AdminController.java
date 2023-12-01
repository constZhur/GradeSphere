package ru.mirea.gradesphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.gradesphere.model.users.Admin;
import ru.mirea.gradesphere.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}
