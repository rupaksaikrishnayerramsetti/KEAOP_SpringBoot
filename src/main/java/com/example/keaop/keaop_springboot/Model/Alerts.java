package com.example.keaop.keaop_springboot.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alerts")
public class Alerts {
    private String user_id;
    private String title;
    private String date;
    private String time;
    private String _id;
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "Alerts [user_id=" + user_id + ", title=" + title + ", date=" + date + ", time=" + time + "]";
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
}
