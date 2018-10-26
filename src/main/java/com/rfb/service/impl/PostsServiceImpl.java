package com.rfb.service.impl;

import com.rfb.domain.Posts;
import com.rfb.repository.PostsRepository;
import com.rfb.service.PostsService;
import com.rfb.service.dto.PostsDTO;
import com.rfb.service.mapper.PostsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class PostsServiceImpl  implements PostsService {


    private final Logger log = LoggerFactory.getLogger(PostsServiceImpl.class);


    private final PostsRepository postsRepository;

    private final PostsMapper postsMapper;

    public PostsServiceImpl(PostsRepository postsRepository, PostsMapper postsMapper) {
        this.postsRepository = postsRepository;
        this.postsMapper = postsMapper;
    }

    /**
     * Save a rfbEventAttendance.
     *
     * @param postsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PostsDTO save(PostsDTO postsDTO) {
     //   log.debug("Request to save Posts : {}", postsDTO);
        //Posts posts = postsMapper.toEntity(postsDTO);
       // posts = postsRepository.save(posts);
        //return postsMapper.toDto(posts);
        return postsDTO;
    }

    /**
     *  Get all the rfbEventAttendances.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PostsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Posts");
        return postsRepository.findAll(pageable)
            .map(postsMapper::toDto);
    }

    /**
     *  Get one rfbEventAttendance by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PostsDTO findOne(Long id) {
        log.debug("Request to get Posts : {}", id);
        Posts posts = postsRepository.findOne(id);
        return postsMapper.toDto(posts);
    }

    /**
     *  Delete the  rfbEventAttendance by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Posts : {}", id);
        postsRepository.delete(id);
    }
    
}
