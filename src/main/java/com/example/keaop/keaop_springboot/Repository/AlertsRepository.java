package com.example.keaop.keaop_springboot.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.keaop.keaop_springboot.Model.Alerts;

public interface AlertsRepository extends MongoRepository<Alerts, String> {
    
}
