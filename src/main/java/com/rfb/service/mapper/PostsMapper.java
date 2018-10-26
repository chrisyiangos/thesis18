package com.rfb.service.mapper;

import com.rfb.domain.Paper;
import com.rfb.domain.Posts;
import com.rfb.service.dto.PaperDTO;
import com.rfb.service.dto.PostsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {PaperMapper.class})
public interface PostsMapper extends EntityMapper<PaperDTO, Paper> {

    @Mapping(source = "paper", target = "paperDTO")
    PostsDTO toDto(Posts posts);

    @Mapping(source = "postsDTO", target = "posts")
    Posts toEntity(PostsDTO postsDTO);
    default Posts fromId(Long id) {
        if (id == null) {
            return null;
        }
        Posts posts = new Posts();
        posts.setId(id);
        return posts;
    }
}
