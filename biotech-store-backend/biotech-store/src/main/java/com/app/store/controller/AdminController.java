package com.app.store.controller;

import com.app.store.entity.User;
import com.app.store.service.CustomUserDetailsService;
import com.app.store.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;
import static org.apache.commons.lang3.StringUtils.left;
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User newUser = adminService.addUser(user);
            return ok(newUser);
        } catch (Exception e) {
            return badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/userName")
    public ResponseEntity<?> getUserName() {
        return ok(customUserDetailsService.getCurrentUsername());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            adminService.deleteUser(id);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting user: " + left(getStackTrace(e), 1000));
        }
    }

}
