package com.rfb.service;

import com.rfb.service.dto.PostsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostsService {

    /**
     * Save a post.
     *
     * @param postsDTO the entity to save
     * @return the persisted entity
     */
    PostsDTO save(PostsDTO postsDTO);

    /**
     *  Get all the posts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PostsDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" post.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PostsDTO findOne(Long id);

    /**
     *  Delete the "id" post.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

}
