package com.sns.board.controller;

import com.sns.board.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @GetMapping("/api/v1/posts")
    public ResponseEntity<List<Post>> getPosts(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", ZonedDateTime.now()));
        posts.add(new Post(2L, "Post 1", ZonedDateTime.now()));
        posts.add(new Post(3L, "Post 1", ZonedDateTime.now()));

        return new ResponseEntity<>(posts, HttpStatus.OK); //response는 json 리스트로 반환
    }
    /*
    * Response entity: 응답 본문 + 상태코드+헤더
     */
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", ZonedDateTime.now()));
        posts.add(new Post(2L, "Post 2", ZonedDateTime.now()));
        posts.add(new Post(3L, "Post 3", ZonedDateTime.now()));


        Optional<Post> matchingPost = posts.stream()
                .filter(post -> id.equals(post.getPostId())) //조건에 맞는 post만 남김
                .findFirst(); // 그중 첫번째만 반환

        return matchingPost
                .map(ResponseEntity::ok) //값이 있으면 ok반환
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
