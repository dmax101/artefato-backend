package br.inate.icc.idp.artefato.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inate.icc.idp.artefato.model.Post;
import br.inate.icc.idp.artefato.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Collection<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }
    
}
