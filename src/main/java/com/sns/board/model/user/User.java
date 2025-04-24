package com.sns.board.model.user;

import com.sns.board.model.entity.UserEntity;

import java.time.ZonedDateTime;

public record User(
        Long userId,
        String username,
        String profile,
        ZonedDateTime createAt,
        ZonedDateTime updateAt) {
        public static User from(UserEntity userEntity){
            return new User
                    (userEntity.getUserId(), userEntity.getUsername(), userEntity.getProfile(), userEntity.getCreateAt(), userEntity.getUpdateAt());
        }
}

