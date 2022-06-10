package com.cab.controller;

import com.cab.model.Admin;
import com.cab.model.UserRoles;
import com.cab.service.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabook/admin")
public class AdminController {

    @Autowired
    private AdminServiceInterface adminServiceInterface;

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable("id") Integer id) {
        return adminServiceInterface.getAdmin(id);
    }

    @GetMapping("/")
    public List<Admin> getAllAdmins() {
        return adminServiceInterface.getAllAdmins();
    }

    @PostMapping("/")
    public Admin insertAdmin(@RequestBody Admin admin) {
        admin.setRoles(List.of(UserRoles.ADMIN));
        return adminServiceInterface.insertAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable("id") Integer id, @RequestBody Admin admin) {
        return adminServiceInterface.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public Admin deleteAdmin(@PathVariable("id") Integer id) {
        return adminServiceInterface.deleteAdmin(id);
    }
}
