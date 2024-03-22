package com.example.keaop.keaop_springboot.Controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.keaop.keaop_springboot.Helpers.DateAndTimeUtil;
import com.example.keaop.keaop_springboot.Helpers.EmailService;
import com.example.keaop.keaop_springboot.Helpers.EmailTemplate;
import com.example.keaop.keaop_springboot.Helpers.JWTService;
import com.example.keaop.keaop_springboot.Model.Alerts;
import com.example.keaop.keaop_springboot.Repository.AlertsRepository;

@RestController
@CrossOrigin(origins = "*")
public class AlertsController {
    private final AlertsRepository arepo;
    private final JWTService js;
    private final EmailService es;
    private final EmailTemplate et;
    private final DateAndTimeUtil dt;

    public AlertsController(AlertsRepository arepo, JWTService js, EmailService es, EmailTemplate et, DateAndTimeUtil dt) {
        this.arepo = arepo;
        this.js = js;
        this.es = es;
        this.et = et;
        this.dt = dt;
    }
    
    @PostMapping("/createAlert")
    public Boolean CreateAlert(@RequestBody Map<String, String> requestBody) {
        try {
            String token = requestBody.get("token");
            Map<String, String> decodedData = js.decodeToken(token);
            String Title = requestBody.get("title");
            Alerts alert = new Alerts();
            alert.setTitle(Title.toUpperCase());
            alert.setDate(requestBody.get("date"));
            alert.setTime(requestBody.get("time"));
            alert.setUser_id(decodedData.get("userId"));
            arepo.save(alert);
            String modifiedDate = dt.modifyDate(requestBody.get("date"));
            String modifiedTime = dt.modifyTime(requestBody.get("time"));
            String googleCalendarLink = dt.generateGoogleCalendarLink(Title, requestBody.get("date"), modifiedTime, "You had an important meeting of " + Title.toUpperCase());
            String template = et.userAlertMsgTemplate(Title, modifiedDate, modifiedTime, googleCalendarLink);
            System.out.println(googleCalendarLink);
            es.sendEmail(decodedData.get("email"), "New Remainder Creation Alert", template);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping("/editAlert")
    public boolean EditAlert(@RequestBody Map<String, String> requestBody) {
        try {
            String token = requestBody.get("token");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            Optional<Alerts> Optionalalert = arepo.findById(requestBody.get("alert_id"));
            if(Optionalalert.isPresent()){
                String Title = requestBody.get("title");
                Alerts alert = Optionalalert.get();
                alert.setTitle(Title.toUpperCase());
                alert.setDate(requestBody.get("date"));
                alert.setTime(requestBody.get("time"));
                alert.setUser_id(userId);
                arepo.save(alert);
                String modifiedDate = dt.modifyDate(requestBody.get("date"));
                String modifiedTime = dt.modifyTime(requestBody.get("time"));
                String googleCalendarLink = dt.generateGoogleCalendarLink(Title, requestBody.get("date"), modifiedTime, "You had an important meeting of " + Title.toUpperCase());
                String template = et.userEditedAlertMsgTemplate(Title, modifiedDate, modifiedTime, googleCalendarLink);
                es.sendEmail(decodedData.get("email"), "Remainder Update Alert", template);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    

    @GetMapping("/fetchAllAlerts")
    public List<Alerts> FetchAllAlerts(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            List<Alerts> Alerts = arepo.findByUserId(userId);
            return Alerts;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @DeleteMapping("/deleteAlert")
    public Boolean DeleteAlert(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            String noteId = requestHeader.get("noteid");
            if (noteId != null) {
                arepo.deleteById(noteId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/getAlertCount")
    public String GetAlertCount(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            List<Alerts> notes = arepo.findByUserId(userId);
            Number count = notes.size();
            return count.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/fetchAlerts")
    public Map<String, Object> FetchAlerts(@RequestHeader Map<String, String> requestHeader) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            List<Alerts> notes = arepo.findByUserId(userId);
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