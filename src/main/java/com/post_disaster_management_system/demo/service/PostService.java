package com.post_disaster_management_system.demo.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.post_disaster_management_system.demo.Exception.NotFoundException;
import com.post_disaster_management_system.demo.Model.PostModel;
import com.post_disaster_management_system.demo.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public String validatePost(MultipartFile image)throws Exception {
        if(image.isEmpty()) {
            throw new NotFoundException("Image Nor Found");
        }
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(image.getInputStream());
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if(directory != null) {
                Date date = directory.getDateOriginal();
                if(checkDate(date)) {
                    return "Success";
                }else throw new Exception("Image Taken Before 24 hours");

            } else throw new NotFoundException("Image Not Taken In System Camera");
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean checkDate(Date date) {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime imageDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return yesterday.isBefore(imageDate);
    }


    public PostModel addPost(PostModel post,MultipartFile image) throws IOException {
        post.setImage(image.getBytes());
        return postRepo.save(post);
    }
}
