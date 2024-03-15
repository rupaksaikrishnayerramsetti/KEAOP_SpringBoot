package com.example.keaop.keaop_springboot.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Notes {
    private String user_id;
    private String title;
    private String value;
    private String _id;
    public String getUser_id() {
        return user_id;
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
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
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Notes [user_id=" + user_id + ", title=" + title + ", value=" + value + "]";
    }
}