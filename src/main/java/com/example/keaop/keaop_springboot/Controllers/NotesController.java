package com.example.keaop.keaop_springboot.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.keaop.keaop_springboot.Helpers.JWTService;
import com.example.keaop.keaop_springboot.Model.Notes;
import com.example.keaop.keaop_springboot.Repository.NotesRepository;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class NotesController {
    private final NotesRepository nrepo;
    private final JWTService JwtService;

    public NotesController(NotesRepository nrepo, JWTService JwtService) {
        this.nrepo = nrepo;
        this.JwtService = JwtService;
    }

    @PostMapping("/createNote")
    public Boolean CreateNote(@RequestBody Map<String, String> requestBody) {
        try {
            String token = requestBody.get("token");
            Map<String, String> decodedData = JwtService.decodeToken(token);
            Notes note = new Notes();
            note.setTitle(requestBody.get("title"));
            note.setValue(requestBody.get("value"));
            note.setUser_id(requestBody.get(decodedData.get("userId")));
            nrepo.save(note);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @GetMapping("/fetchAllNotes")
    public Optional<Notes> FetchAllNotes(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("token");
            Map<String, String> decodedData = JwtService.decodeToken(token);
            String userId = decodedData.get("userId");
            Optional<Notes> notes = nrepo.findById(userId);
            return notes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @PostMapping("/editNote")
    public Boolean EditNote(@RequestBody Map<String, String> requestBody) {
        try {
            String token = requestBody.get("token");
            Map<String, String> decodedData = JwtService.decodeToken(token);
            String userId = decodedData.get("userId");
            Optional<Notes> Optionalnote = nrepo.findById(requestBody.get("note_id"));
            if(Optionalnote.isPresent()){
                Notes note = Optionalnote.get();
                note.setTitle(requestBody.get("title"));
                note.setUser_id(userId);
                note.setValue(requestBody.get("value"));
                nrepo.save(note);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
