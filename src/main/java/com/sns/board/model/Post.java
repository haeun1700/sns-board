package com.sns.board.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Post {
    private Long postId;
    private String body;
    private ZonedDateTime createdAt; // 글로벌 data형식

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return Objects.equals(getPostId(), post.getPostId()) && Objects.equals(getBody(), post.getBody()) && Objects.equals(getCreatedAt(), post.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getBody(), getCreatedAt());
    }

    public Post(Long postId, String body, ZonedDateTime createdAt) {
        this.postId = postId;
        this.body = body;
        this.createdAt = createdAt;
    }

    public Post(){

    }
}
