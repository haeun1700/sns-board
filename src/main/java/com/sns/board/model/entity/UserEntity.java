package com.sns.board.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATE \"user\" SET deleteat = CURRENT_TIMESTAMP WHERE userid = ?")
@SQLRestriction("deleteAt IS NULL")
public class UserEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String profile;

    @Column
    private String description;

    @Column
    private ZonedDateTime createAt;

    @Column
    private ZonedDateTime updateAt;

    @Column
    private ZonedDateTime deleteAt;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getProfile(), that.getProfile()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCreateAt(), that.getCreateAt()) && Objects.equals(getUpdateAt(), that.getUpdateAt()) && Objects.equals(getDeleteAt(), that.getDeleteAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getPassword(), getProfile(), getDescription(), getCreateAt(), getUpdateAt(), getDeleteAt());
    }

    public static UserEntity of(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);

        // 랜던한 프로필 사진 설정(1~100)
        userEntity.setProfile("https://avator.iran.liara.run/public/" + new Random().nextInt(100));

        return userEntity;
    }

    @PrePersist
    private void prePersist(){
        this.createAt = ZonedDateTime.now();
        this.updateAt = this.createAt;
    }
    @PreUpdate
    private void preUpdate(){
        updateAt = ZonedDateTime.now();
    }
}

