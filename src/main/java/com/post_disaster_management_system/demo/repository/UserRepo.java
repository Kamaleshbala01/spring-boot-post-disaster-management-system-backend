package com.post_disaster_management_system.demo.repository;

import com.post_disaster_management_system.demo.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserModel,String> {
    public UserModel findByUsername(String username);
}
