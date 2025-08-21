package com.post_disaster_management_system.demo.controller;


import com.post_disaster_management_system.demo.Exception.UsernameUnavailableException;
import com.post_disaster_management_system.demo.Model.UserModel;
import com.post_disaster_management_system.demo.service.UserService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public List<UserModel> test() {
        return userService.findAll();
    }

    @PostMapping("/register")
    public UserModel register(@RequestBody UserModel user) throws UsernameUnavailableException {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel user) {
        try {
            return ResponseEntity.ok().body(userService.login(user));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllUser")
    public List<UserModel> getAllUser() {
        try {
            return userService.findAll();
        }catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/verifyMobile")
    public ResponseEntity<String> verifyMobile(@RequestBody UserModel user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.verifyMobile(user.getPhoneNumber()));
    }

    @PatchMapping("/updateAvailable")
    public ResponseEntity<UserModel> updateAvailability(@RequestBody Map<String,Boolean> request) throws Exception {
        if(!request.containsKey("available")) throw new Exception("Bad Request");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.updateAvailable(username,request.get("available")),HttpStatus.OK);
    }

    @PatchMapping("/assignPost/{id}")
    public ResponseEntity<UserModel> assignPost(@PathVariable String id) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.assignPost(username,id),HttpStatus.OK);
    }
}
