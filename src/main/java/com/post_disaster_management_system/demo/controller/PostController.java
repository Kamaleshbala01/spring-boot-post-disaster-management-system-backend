package com.post_disaster_management_system.demo.controller;

import com.post_disaster_management_system.demo.Model.PostModel;
import com.post_disaster_management_system.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("addPost")
    public ResponseEntity<PostModel> addPost(@RequestBody PostModel post,@RequestParam("file") MultipartFile image) throws IOException {
        PostModel resPost = postService.addPost(post,image);
        return new ResponseEntity<>(resPost, HttpStatus.CREATED);
    }

    @PostMapping("/validateImage")
    public ResponseEntity<Object> validateImage(@RequestParam("file") MultipartFile image) throws Exception {
        return ResponseEntity.ok().body(postService.validatePost(image));
    }
}
