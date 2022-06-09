package com.cab.controller;

import com.cab.model.Admin;
import com.cab.model.UserRoles;
import com.cab.service.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminServiceInterface adminServiceInterface;

    @GetMapping("/admins/{id}")
    public Admin getAdmin(@PathVariable("id") Integer id) {
        return adminServiceInterface.getAdmin(id);
    }

    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminServiceInterface.getAllAdmins();
    }

    @PostMapping("/admins")
    public Admin insertAdmin(@RequestBody Admin admin) {
        admin.setRoles(List.of(UserRoles.ADMIN));
        return adminServiceInterface.insertAdmin(admin);
    }

    @PutMapping("/admins/{id}")
    public Admin updateAdmin(@PathVariable("id") Integer id, @RequestBody Admin admin) {
        return adminServiceInterface.updateAdmin(id, admin);
    }

    @DeleteMapping("/admins/{id}")
    public Admin deleteAdmin(@PathVariable("id") Integer id) {
        return adminServiceInterface.deleteAdmin(id);
    }
}
