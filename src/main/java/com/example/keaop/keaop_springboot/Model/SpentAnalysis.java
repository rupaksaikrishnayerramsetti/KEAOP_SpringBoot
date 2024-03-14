package com.example.keaop.keaop_springboot.Model;

public class SpentAnalysis {
    private String user_id;
    private Object spent_data;
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public Object getSpent_data() {
        return spent_data;
    }
    public void setSpent_data(Object spent_data) {
        this.spent_data = spent_data;
    }
    @Override
    public String toString() {
        return "SpentAnalysis [user_id=" + user_id + ", spent_data=" + spent_data + "]";
    }
}