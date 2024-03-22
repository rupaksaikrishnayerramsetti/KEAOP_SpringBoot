package com.example.keaop.keaop_springboot.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.keaop.keaop_springboot.Model.Users;
import com.example.keaop.keaop_springboot.Repository.UsersRepository;

@Service
public class SpentAnalysisUtility {
    private final UsersRepository urepo;

    public SpentAnalysisUtility(UsersRepository urepo) {
        this.urepo = urepo;
    }

    public int getSalary(String userId) {
        Optional<Users> userOptional = urepo.findById(userId);
        if(userOptional!=null) {
            Users user = userOptional.get();
            return user.getSalary();
        }
        return 0;
    }

    public Map<String, Map<String, Integer>> createJson(String userId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
        String currentMonthYear = dateFormat.format(new Date());

        int salary = getSalary(userId);

        Map<String, Map<String, Integer>> data = new HashMap<>();
        Map<String, Integer> innerMap = new HashMap<>();
        innerMap.put("PG", 0);
        innerMap.put("Food", 0);
        innerMap.put("Bills", 0);
        innerMap.put("Traveling", 0);
        innerMap.put("Shopping", 0);
        innerMap.put("Other", 0);
        innerMap.put("Savings", salary);

        data.put(currentMonthYear, innerMap);

        return data;
    }

    public Map<String, Map<String, Integer>> jsonForCurrentMonth(String userId, Map<String, Map<String, Integer>> data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
        String currentMonthYear = dateFormat.format(new Date());

        int salary = getSalary(userId);

        Map<String, Integer> currentMonthData = new HashMap<>();
        currentMonthData.put("PG", 0);
        currentMonthData.put("Food", 0);
        currentMonthData.put("Bills", 0);
        currentMonthData.put("Traveling", 0);
        currentMonthData.put("Shopping", 0);
        currentMonthData.put("Other", 0);
        currentMonthData.put("Savings", salary);

        data.put(currentMonthYear, currentMonthData);

        return data;
    }

}