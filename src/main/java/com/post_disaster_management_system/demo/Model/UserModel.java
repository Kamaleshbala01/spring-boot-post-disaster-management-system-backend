package com.post_disaster_management_system.demo.Model;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserModel {
    @Indexed(unique = true)
    private String username;
    private String password;
    private String phoneNumber;
    private Boolean isAvailable = true;
    private String assignedPost;

    public String getAssignedPost() {
        return assignedPost;
    }

    public void setAssignedPost(String assignedPost) {
        this.assignedPost = assignedPost;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
