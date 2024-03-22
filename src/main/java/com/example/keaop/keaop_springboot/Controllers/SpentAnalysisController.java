package com.example.keaop.keaop_springboot.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.keaop.keaop_springboot.Helpers.JWTService;
import com.example.keaop.keaop_springboot.Helpers.SpentAnalysisUtility;
import com.example.keaop.keaop_springboot.Model.SpentAnalysis;
import com.example.keaop.keaop_springboot.Repository.SpentAnalysisRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SpentAnalysisController {
    private final SpentAnalysisRepository srepo;
    private final JWTService js;
    private final SpentAnalysisUtility su;

    public SpentAnalysisController(SpentAnalysisRepository srepo, JWTService js, SpentAnalysisUtility su) {
        this.srepo = srepo;
        this.js = js;
        this.su = su;
    }

    @PostMapping("/updateSpentRecord")
    public Boolean UpdateSpentRecord(@RequestBody Map<String, String> requestBody) {
        try {
            String token = requestBody.get("token");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            String spent_type = requestBody.get("spenttype");
            String amount = requestBody.get("amount");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
            String currentMonthYear = dateFormat.format(new Date());
            SpentAnalysis completeRecord = srepo.findByUserId(userId);
            Map<String, Map<String, Integer>> completeDataRecord = completeRecord.getSpent_data();
            completeDataRecord.get(currentMonthYear).compute(spent_type, (k, v) -> v != null ? v + Integer.parseInt(amount, 10) : Integer.parseInt(amount, 10));
            completeDataRecord.get(currentMonthYear).compute("Savings", (k, v) -> v - Integer.parseInt(amount, 10));
            completeRecord.setSpent_data(completeDataRecord);
            srepo.save(completeRecord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @GetMapping("/fetchCurrentMonthRecord")
    public Map<String, Map<String, Integer>> FetchCurrentMonthRecord(@RequestHeader Map<String, String> requestHeader) {
        try {
            String token = requestHeader.get("authorization");
            Map<String, String> decodedData = js.decodeToken(token);
            String userId = decodedData.get("userId");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
            String currentMonthYear = dateFormat.format(new Date());
            SpentAnalysis result1 = srepo.findByUserId(userId);
            if(result1==null) {
                Map<String, Map<String, Integer>> spentData = su.createJson(userId);
                SpentAnalysis result2 = new SpentAnalysis();
                result2.setSpent_data(spentData);
                result2.setUser_id(userId);
                srepo.save(result2);
                return spentData;
            } else {
                Map<String, Map<String, Integer>> spentData = result1.getSpent_data();
                if(spentData.get(currentMonthYear)!=null) {
                    return spentData;
                } else {
                    Map<String, Map<String, Integer>> result3 = su.jsonForCurrentMonth(userId, spentData);
                    SpentAnalysis result4 = new SpentAnalysis();
                    result4.setSpent_data(result3);
                    result4.setUser_id(userId);
                    result4.set_id(result1.get_id());
                    srepo.save(result4);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
