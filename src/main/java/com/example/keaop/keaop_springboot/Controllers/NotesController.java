package com.example.keaop.keaop_springboot.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.keaop.keaop_springboot.Helpers.JWTService;
import com.example.keaop.keaop_springboot.Model.Notes;
import com.example.keaop.keaop_springboot.Repository.NotesRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class NotesController {
    private final NotesRepository nrepo;
    private final JWTService js;

    public NotesController(NotesRepository nrepo, JWTService js) {
        this.nrepo = nrepo;
        this.js = js;
    }

    @PostMapping("/createNote")
    public Boolean CreateNote(@RequestBody Map<String, String> requestBody) {
        Boolean response;
        try {
            String token = requestBody.get("token");
            Map<String, String> decodedData = js.decodeToken(token);
            Notes note = new Notes();
            note.setTitle(requestBody.get("title"));
            note.setValue(requestBody.get("value"));
            note.setUser_id(decodedData.get("userId"));
            nrepo.save(note);
            response = true;
        } catch (Exception e) {
            e.printStackTrace();
            response = false;
        }
        return response;
    }
    
    @GetMapping("/fetchAllNotes")
    public List<Notes> fetchAllNotes(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            List<Notes> notes = nrepo.findByUserId(userId);
            return notes;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    @PostMapping("/editNote")
    public Boolean EditNote(@RequestBody Map<String, String> requestBody) {
        try {
            String token = requestBody.get("token");
            Map<String, String> decodedData = js.decodeToken(token);
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
    
    @DeleteMapping("/deleteNote")
    public Boolean deleteNote(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            String noteId = requestHeader.get("noteid");
            if (noteId != null) {
                nrepo.deleteById(noteId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/getNoteCount")
    public String GetNoteCount(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            List<Notes> notes = nrepo.findByUserId(userId);
            Number count = notes.size();
            return count.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/fetchNotes")
    public Map<String, Object> FetchNotes(@RequestHeader Map<String, String> requestHeader) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            List<Notes> notes = nrepo.findByUserId(userId);
            Number count = notes.size();
            response.put("count", count);
            response.put("data", notes);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("count", 0);
            response.put("data", Collections.emptyList());
        }
        return response;
    }
    
}