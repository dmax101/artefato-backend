package br.inate.icc.idp.artefato.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inate.icc.idp.artefato.model.PostEntity;
import br.inate.icc.idp.artefato.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Collection<PostEntity> getAllPosts() {
        log.info("Getting all posts");
        return postRepository.getAllPosts();
    }
    
}
