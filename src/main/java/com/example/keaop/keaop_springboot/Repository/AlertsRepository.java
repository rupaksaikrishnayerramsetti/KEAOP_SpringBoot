package com.example.keaop.keaop_springboot.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.keaop.keaop_springboot.Model.Alerts;

public interface AlertsRepository extends MongoRepository<Alerts, String> {
    @Query("{'user_id' : ?0 }")
    List<Alerts> findByUserId(String userId);
}
