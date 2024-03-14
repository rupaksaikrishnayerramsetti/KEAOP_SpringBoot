package com.example.keaop.keaop_springboot.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.keaop.keaop_springboot.Model.Notes;

public interface NotesRepository extends MongoRepository<Notes, String> {
    
}
