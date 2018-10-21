package com.rfb.service.impl;

import com.rfb.domain.Paper;
import com.rfb.repository.PaperRepository;
import com.rfb.service.PaperService;
import com.rfb.service.dto.PaperDTO;
import com.rfb.service.mapper.PaperMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {

    private final Logger log = LoggerFactory.getLogger(PaperServiceImpl.class);

    private final PaperRepository paperRepository;

    private final PaperMapper paperMapper;


    public PaperServiceImpl(PaperRepository paperRepository, PaperMapper paperMapper) {
        this.paperRepository = paperRepository;
        this.paperMapper = paperMapper;
    }

    /**
     * Save a rfbEventAttendance.
     *
     * @param paperDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PaperDTO save(PaperDTO paperDTO) {
        log.debug("Request to save Paper : {}", paperDTO);
        Paper paper = paperMapper.toEntity(paperDTO);
        paper = paperRepository.save(paper);
        return paperMapper.toDto(paper);
    }

    /**
     *  Get all the rfbEventAttendances.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaperDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Papers");
        return paperRepository.findAll(pageable)
            .map(paperMapper::toDto);
    }

    /**
     *  Get one rfbEventAttendance by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PaperDTO findOne(Long id) {
        log.debug("Request to get Paper : {}", id);
        Paper paper = paperRepository.findOne(id);
        return paperMapper.toDto(paper);
    }

    /**
     *  Delete the  rfbEventAttendance by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Paper : {}", id);
        paperRepository.delete(id);
    }
}
