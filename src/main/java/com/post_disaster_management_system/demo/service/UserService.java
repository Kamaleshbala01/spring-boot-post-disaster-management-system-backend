package com.post_disaster_management_system.demo.service;

import com.post_disaster_management_system.demo.Exception.NotFoundException;
import com.post_disaster_management_system.demo.Exception.UsernameUnavailableException;
import com.post_disaster_management_system.demo.Model.UserModel;
import com.post_disaster_management_system.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;
    public List<UserModel> findAll() {
        return userRepo.findAll();
    }

    public UserModel register(UserModel user) throws UsernameUnavailableException {
        UserModel existUser = userRepo.findByUsername(user.getUsername());
        if(existUser != null) {
            throw new UsernameUnavailableException("User name " + user.getUsername() + " Already Taken");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public String login(UserModel user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "";
    }

    public String verifyMobile(String mobile){
        return "";
    }

    public UserModel updateAvailable(String username,boolean available) {
        UserModel existUser = userRepo.findByUsername(username);
        if(existUser == null) {
            throw new NotFoundException("User not found");
        }
        existUser.setAvailable(available);
        return userRepo.save(existUser);
    }

    public UserModel assignPost(String username, String id) {
        UserModel user = userRepo.findByUsername(username);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        user.setAssignedPost(id);
        return userRepo.save(user);
    }
}
