package com.sns.board.model;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
public class Post{
    private Long id;
    private String body;
    private ZonedDateTime createdAt;

    public Post(long id, String body, ZonedDateTime createdAt) {
        this.id = id;
        this.body = body;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getBody(), post.getBody()) && Objects.equals(getCreatedAt(), post.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBody(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
