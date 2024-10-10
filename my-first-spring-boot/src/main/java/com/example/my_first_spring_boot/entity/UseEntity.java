package com.example.my_first_spring_boot.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "user")
public class UseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_num;
    @Column(nullable = false, length = 20, unique = true)
    private String id;
    @Column(nullable = false, length = 50)
    private String pass;
    @Column(nullable = true, length = 50)
    private String name;
    @Column(nullable = false)
    private String role = "USER";
    @Column
    private Date birth;
    @Column
    private String e_mail;
    @Column
    private String phone;

    @Override
    public String toString() {
        return "UseEntity{" +
                "user_num=" + user_num +
                ", id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", birth=" + birth +
                ", e_mail='" + e_mail + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_num() {
        return user_num;
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
