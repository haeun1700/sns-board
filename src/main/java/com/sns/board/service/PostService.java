package com.sns.board.service;

import com.sns.board.exception.post.PostNotFoundException;
import com.sns.board.model.Post;
import com.sns.board.model.PostPatchRequestBody;
import com.sns.board.model.PostPostRequestBody;
import com.sns.board.model.entity.PostEntity;
import com.sns.board.repository.PostEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    @Autowired private PostEntityRepository postEntityRepository;

    public List<Post> getPosts() {
        List<PostEntity> posts = postEntityRepository.findAll();

        return posts.stream().map(Post::from).toList(); // repository를 dto로 변경해서 반환
    }

    public Post getPost(Long id) {
        PostEntity post = postEntityRepository
                .findById(id) // 해당 데이터가 존재하지 않을 경우도 있어 서비스에서 예외 던지기, 만약 optional로 감싸면 컨트롤러에서 예외를 제어할 필요가 있다.
                .orElseThrow(() -> new PostNotFoundException(id));
          return Post.from(post); // static으로 선언되어 있어 메서드 호출 가능
    }


    public Post createPost(PostPostRequestBody postPostRequestBody) {
        //1. 요청 DTO로부터 데이터 넣기
        PostEntity postEntity = new PostEntity();
        postEntity.setBody(postPostRequestBody.body()); // body데이터 넣기

        //2. 저장된 엔티티 정보를 DTO로 반환
        return Post.from(postEntityRepository.save(postEntity));
    }

    public Post updatePost(Long id, PostPatchRequestBody postPatchRequestBody) {
        PostEntity post = postEntityRepository
                .findById(id) // 해당 데이터가 존재하지 않을 경우도 있어 서비스에서 예외 던지기, 만약 optional로 감싸면 컨트롤러에서 예외를 제어할 필요가 있다.
                .orElseThrow(() -> new PostNotFoundException(id));
       post.setBody(postPatchRequestBody.body());
       return Post.from(postEntityRepository.save(post));
    }

    public void deletePost(Long id) {
        PostEntity postEntity = postEntityRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        postEntityRepository.delete(postEntity);
    }

}
