package com.rfb.service;

import com.rfb.service.dto.PaperDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaperService {

    /**
     * Save a rfbEventAttendance.
     *
     * @param paperDTO the entity to save
     * @return the persisted entity
     */
    PaperDTO save(PaperDTO paperDTO);

    /**
     *  Get all the rfbEventAttendances.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PaperDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" rfbEventAttendance.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PaperDTO findOne(Long id);

    /**
     *  Delete the "id" rfbEventAttendance.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
