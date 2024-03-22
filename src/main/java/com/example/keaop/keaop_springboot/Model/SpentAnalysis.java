package com.example.keaop.keaop_springboot.Model;

import java.util.Map;

public class SpentAnalysis {
    private String user_id;
    private Map<String, Map<String, Integer>> spent_data;
    private String _id;
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public Map<String, Map<String, Integer>> getSpent_data() {
        return spent_data;
    }
    public void setSpent_data(Map<String, Map<String, Integer>> spent_data) {
        this.spent_data = spent_data;
    }
    @Override
    public String toString() {
        return "SpentAnalysis [user_id=" + user_id + ", spent_data=" + spent_data + "]";
    }
}