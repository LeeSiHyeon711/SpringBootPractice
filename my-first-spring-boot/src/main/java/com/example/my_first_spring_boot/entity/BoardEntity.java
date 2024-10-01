package com.example.my_first_spring_boot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // 처음 저장될 때 현재 시간을 createdAt에 할당
    // 초 단위 값은 제거함
    @PrePersist
    protected void onCreate(){
        this.createDate = LocalDateTime.now().withSecond(0).withNano(0);
    }

    @Override
    public String toString() {
        return "BoardEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", views=" + views +
                ", likes=" + likes +
                '}';
    }

    public int getLikes() { return likes; }

    public void setLikes(int likes) { this.likes = likes; }

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

    public void setCreateDate(LocalDateTime createdAt) {
        this.createDate = createdAt;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
