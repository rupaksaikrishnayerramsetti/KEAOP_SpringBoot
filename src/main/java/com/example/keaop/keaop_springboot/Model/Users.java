package com.example.keaop.keaop_springboot.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
    private String user_name;
    private String email;
    private String gender;
    private String occupation;
    private String phone_number;
    private int salary;
    private String password_digest;
    private String _id;
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getOccupation() {
        return occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getPassword_digest() {
        return password_digest;
    }
    public void setPassword_digest(String password_digest) {
        this.password_digest = password_digest;
    }
    @Override
    public String toString() {
        return "Users [user_name=" + user_name + ", email=" + email + ", gender=" + gender + ", occupation="
                + occupation + ", phone_number=" + phone_number + ", salary=" + salary + ", password_digest="
                + password_digest + "]";
    }
}