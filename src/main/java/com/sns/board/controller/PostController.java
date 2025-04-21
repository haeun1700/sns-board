package com.sns.board.controller;

import com.sns.board.model.Post;
import com.sns.board.model.PostPostRequestBody;
import com.sns.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService; // 의존성 주입으로 객체 생성을 스프링에게 맡긴다.

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(){
        List<Post> posts = postService.getPosts();
        return ResponseEntity.ok(posts); //response는 json 리스트로 반환
    }
    /*
    * Response entity: 응답 본문 + 상태코드+헤더
    * Optional: 특정 게시글 or null 반환 -> 단건 map()이 있어 쉽게 반환 형태 가능
    * 만약 List가 반환 타입이라면 []이 반환된다. ->단건 map()이 없어 get(0)을 추가해야함.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        Optional<Post> matchingPost = postService.getPost(id);
        return matchingPost
                .map(ResponseEntity::ok) //값이 있으면 ok반환
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostPostRequestBody postPostRequestBody){
        Post post = postService.createPost(postPostRequestBody);
        return ResponseEntity.ok(post);
    }
}
