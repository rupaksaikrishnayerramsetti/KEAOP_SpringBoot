package com.example.keaop.keaop_springboot.Repository;

import com.example.keaop.keaop_springboot.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsersRepository extends MongoRepository<Users, String> {
    @Query("{ 'email' : ?0 ,'password_digest' : ?1 }")
    Users findByEmailAndPasswordDigest(String email,String passwordDigest);
    @Query("{ 'email' : ?0 }")
    Users findByEmail(String email);
}
