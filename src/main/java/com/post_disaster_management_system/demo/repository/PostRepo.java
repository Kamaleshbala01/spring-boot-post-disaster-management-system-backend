package com.post_disaster_management_system.demo.repository;

import com.post_disaster_management_system.demo.Model.PostModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends MongoRepository<PostModel,String> {
}
