package com.post_disaster_management_system.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "posts")
public class PostModel {
    private byte[] image;
    private Double[] location;
    private String postId;
    public PostModel(){
        postId = UUID.randomUUID().toString();
        location = new Double[2];
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Double[] getLocation() {
        return location;
    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
