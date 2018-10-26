package com.rfb.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.rfb.service.PostsService;
import com.rfb.service.dto.PostsDTO;
import com.rfb.web.rest.util.HeaderUtil;
import com.rfb.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

//@RestController
@RequestMapping("/api")
public class PostsResource {

    private final Logger log = LoggerFactory.getLogger(PostsResource.class);

    private static final String ENTITY_NAME = "posts";

    private final PostsService postsService;
    
    public PostsResource(PostsService postsService) {
        this.postsService = postsService;
    }


    /**
     * POST  /posts : Create a new posts.
     *
     * @param postsDTO the postsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new postsDTO, or with status 400 (Bad Request) if the posts has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/posts")
    @Timed
    public ResponseEntity<PostsDTO> createPosts(@RequestBody PostsDTO postsDTO) throws URISyntaxException {
        log.debug("REST request to save Posts : {}", postsDTO);
        if (postsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new posts cannot already have an ID")).body(null);
        }
        PostsDTO result = postsService.save(postsDTO);
        return ResponseEntity.created(new URI("/api/posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /posts : Updates an existing posts.
     *
     * @param postsDTO the postsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated postsDTO,
     * or with status 400 (Bad Request) if the postsDTO is not valid,
     * or with status 500 (Internal Server Error) if the postsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/posts")
    @Timed
    public ResponseEntity<PostsDTO> updatePosts(@RequestBody PostsDTO postsDTO) throws URISyntaxException {
        log.debug("REST request to update Posts : {}", postsDTO);
        if (postsDTO.getId() == null) {
            return createPosts(postsDTO);
        }
        PostsDTO result = postsService.save(postsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, postsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /posts : get all the posts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of postss in body
     */
    @GetMapping("/posts")
    @Timed
    public ResponseEntity<List<PostsDTO>> getAllPostss(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Posts");
        Page<PostsDTO> page = postsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/posts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /posts/:id : get the "id" posts.
     *
     * @param id the id of the postsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the postsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/posts/{id}")
    @Timed
    public ResponseEntity<PostsDTO> getPosts(@PathVariable Long id) {
        log.debug("REST request to get Posts : {}", id);
        PostsDTO postsDTO = postsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(postsDTO));
    }

    /**
     * DELETE  /posts/:id : delete the "id" posts.
     *
     * @param id the id of the postsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/posts/{id}")
    @Timed
    public ResponseEntity<Void> deletePosts(@PathVariable Long id) {
        log.debug("REST request to delete Posts : {}", id);
        postsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    
}
