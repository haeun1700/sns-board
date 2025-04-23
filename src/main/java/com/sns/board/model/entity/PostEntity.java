package com.sns.board.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
//@SQLDelete(sql = "UPDATE \"post\" SET deleteat = CURRENT_TIMESTAMP WHERE postid = ?")
@Table(name = "post")
@SQLDelete(sql = "UPDATE \"post\" SET deleteat = CURRENT_TIMESTAMP WHERE postid = ?")
@SQLRestriction("deleteAt IS NULL")
public class PostEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    @Column
    private ZonedDateTime deleteAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostEntity that)) return false;
        return Objects.equals(getPostId(), that.getPostId()) && Objects.equals(getBody(), that.getBody()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getBody(), getCreatedAt(), getUpdatedAt());
    }
}
