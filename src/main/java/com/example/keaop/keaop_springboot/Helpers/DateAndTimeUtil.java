package com.example.keaop.keaop_springboot.Helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
public class DateAndTimeUtil {
    public String modifyDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US);
            return parsedDate.format(outputFormat);
        } catch (DateTimeParseException e) {
            return "Invalid Date";
        }
    }

    public String modifyTime(String time) {
        try {
            LocalTime parsedTime = LocalTime.parse(time);
            LocalDate currentDate = LocalDate.now();
            ZonedDateTime istTime = ZonedDateTime.of(currentDate, parsedTime, ZoneId.of("Asia/Kolkata"));
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
            return istTime.format(outputFormat);
        } catch (DateTimeParseException e) {
            return "Invalid Time";
        }
    }
    
    public String generateGoogleCalendarLink(String title, String date, String time, String description) {
        try {
            // Parse the date
            LocalDate localDate = LocalDate.parse(date);
    
            // Parse the time and adjust it to 24-hour format
            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a"));
            if (time.contains("PM")) {
                localTime = localTime.plusHours(12);
            }
    
            // Combine date and time into a LocalDateTime object
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
    
            // Convert to ZonedDateTime in IST time zone
            ZonedDateTime istDateTime = localDateTime.atZone(ZoneId.of("Asia/Kolkata"));
    
            // Format date and time as required by Google Calendar
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
            String formattedDateStart = istDateTime.format(formatter);
    
            // Adjust end time by adding 30 minutes
            ZonedDateTime endTime = istDateTime.plusMinutes(30);
            String formattedDateEnd = endTime.format(formatter);
    
            // Encode title and description
            String encodedTitle = URLEncoder.encode(title, "UTF-8");
            String encodedDescription = URLEncoder.encode(description, "UTF-8");
    
            // Create Google Calendar link
            String link = "https://www.google.com/calendar/render"
                    + "?action=TEMPLATE"
                    + "&text=" + encodedTitle
                    + "&dates=" + formattedDateStart + "/" + formattedDateEnd
                    + "&details=" + encodedDescription;
    
            return link;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
