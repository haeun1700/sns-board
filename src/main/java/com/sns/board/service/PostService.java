package com.sns.board.service;

import com.sns.board.model.Post;
import com.sns.board.model.PostPatchRequestBody;
import com.sns.board.model.PostPostRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private static final List<Post> posts = new ArrayList<>();

    static {
        posts.add(new Post(1L, "Post 1", ZonedDateTime.now()));
        posts.add(new Post(2L, "Post 2", ZonedDateTime.now()));
        posts.add(new Post(3L, "Post 3", ZonedDateTime.now()));
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Optional<Post> getPost(Long id) {
        return posts.stream().filter(post -> id.equals(post.getId())).findFirst();
    }

    public Post createPost(PostPostRequestBody postPostRequestBody) {
        Long newPostId = posts.stream().mapToLong(Post::getId).max().orElse(0L)+1;

        Post newPost = new Post(newPostId, postPostRequestBody.body(), ZonedDateTime.now());
        posts.add(newPost);

        return newPost;
    }

    public Post updatePost(Long id, PostPatchRequestBody postPatchRequestBody) {
        // id에 해당하는 post가져오기
        Optional<Post> optionalPost =
                posts.stream().filter(post -> post.getId().equals(id)).findFirst();
        // 그 id body값을 받아 다시 그 아이디에 add해주기
        if(optionalPost.isPresent()){
            Post updatePost = optionalPost.get();
            updatePost.setBody(postPatchRequestBody.body());

            return updatePost;
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }
    }
}
