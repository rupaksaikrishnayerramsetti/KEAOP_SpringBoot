package com.example.keaop.keaop_springboot.Repository;

import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.keaop.keaop_springboot.Model.SpentAnalysis;

public interface SpentAnalysisRepository extends MongoRepository<SpentAnalysis, String> {
    @Query("{'user_id' : ?0 }")
    SpentAnalysis findByUserId(String userId);
}
