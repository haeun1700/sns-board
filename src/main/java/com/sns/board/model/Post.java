package com.sns.board.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sns.board.model.entity.PostEntity;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL) //none이 아닐때만 record로 변환해서 반환
public record Post(
        Long postId,
        String body,
        ZonedDateTime createAt,
        ZonedDateTime updateAt){
        public static Post from(PostEntity postEntity){
            return new Post(
                    postEntity.getPostId(),
                    postEntity.getBody(),
                    postEntity.getCreatedAt(),
                    postEntity.getUpdatedAt());
        }

}
