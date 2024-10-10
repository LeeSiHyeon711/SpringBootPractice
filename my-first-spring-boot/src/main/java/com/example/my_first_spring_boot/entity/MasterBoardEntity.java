package com.example.my_first_spring_boot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "masterBoard")
public class MasterBoardEntity {
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 20)
    private String author;
    @Column(nullable = false, length = 300)
    private String content;
    private LocalDateTime createDate;
    private int views;
    private int likes = 0;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UseEntity user;

    @Override
    public String toString() {
        return "MasterBoardEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", views=" + views +
                ", likes=" + likes +
                ", user=" + user +
                '}';
    }

    public UseEntity getUser() {
        return user;
    }

    public void setUser(UseEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
