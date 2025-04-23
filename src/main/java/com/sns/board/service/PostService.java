package com.sns.board.service;

import com.sns.board.model.Post;
import com.sns.board.model.PostPatchRequestBody;
import com.sns.board.model.PostPostRequestBody;
import com.sns.board.model.entity.PostEntity;
import com.sns.board.repository.PostEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired private PostEntityRepository postEntityRepository;

    private static final List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        List<PostEntity> posts = postEntityRepository.findAll();

        return posts.stream().map(Post::from).toList(); // repository를 dto로 변경해서 반환
    }

    public Post getPost(Long id) {
        PostEntity post = postEntityRepository
                .findById(id) // 해당 데이터가 존재하지 않을 경우도 있어 서비스에서 예외 던지기, 만약 optional로 감싸면 컨트롤러에서 예외를 제어할 필요가 있다.
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글을 찾을 수 없습니다."));
          return Post.from(post); // static으로 선언되어 있어 메서드 호출 가능
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

    public void deletePost(Long id){
        Optional<Post> deletePost = posts.stream().filter(post -> id.equals(post.getId())).findFirst();
        if(deletePost.isPresent()){
            posts.remove(deletePost.get());
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 게시글이 존재하지 않습니다.");
        }
    }
}
