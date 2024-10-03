package com.example.my_first_spring_boot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_num;
    @Column(nullable = false, length = 20)
    private String id;
    @Column(nullable = false, length = 50)
    private String pass;
    @ManyToOne
    @JoinColumn(name = "name")
    public CommentEntity comment;

    @Override
    public String toString() {
        return "UseEntity{" +
                "user_num=" + user_num +
                ", id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                ", comment=" + comment +
                '}';
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

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }
}
