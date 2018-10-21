package com.rfb.service.mapper;

import com.rfb.domain.Paper;
import com.rfb.service.dto.PaperDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RfbEventMapper.class, UserMapper.class, })
public interface PaperMapper extends EntityMapper <PaperDTO, Paper> {

    @Mapping(source = "rfbEvent", target = "rfbEventDTO")
    @Mapping(source = "user", target = "userDTO")
    PaperDTO toDto(Paper paper);

    @Mapping(source = "rfbEventDTO", target = "rfbEvent")
    @Mapping(source = "userDTO", target = "user")
    Paper toEntity(PaperDTO paperDTO);
    default Paper fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paper paper = new Paper();
        paper.setId(id);
        return paper;
    }
}
