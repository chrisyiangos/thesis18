package com.rfb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.rfb.service.PaperService;
import com.rfb.service.dto.PaperDTO;
import com.rfb.web.rest.util.HeaderUtil;
import com.rfb.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

@RestController
@RequestMapping("/api")
public class PaperResource {

    private final Logger log = LoggerFactory.getLogger(PaperResource.class);

    private static final String ENTITY_NAME = "paper";

    private final PaperService paperService;

    public PaperResource(PaperService paperService) {
        this.paperService = paperService;
    }

    /**
     * POST  /paper : Create a new paper.
     *
     * @param paperDTO the paperDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rfbEventAttendanceDTO, or with status 400 (Bad Request) if the rfbEventAttendance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/paper")
    @Timed
    public ResponseEntity<PaperDTO> createPaper(@RequestBody PaperDTO paperDTO) throws URISyntaxException {
        log.debug("REST request to save Paper : {}", paperDTO);
        if (paperDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new paper cannot already have an ID")).body(null);
        }
        PaperDTO result = paperService.save(paperDTO);
        return ResponseEntity.created(new URI("/api/paper/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rfb-event-attendances : Updates an existing rfbEventAttendance.
     *
     * @param paperDTO the paperDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rfbEventAttendanceDTO,
     * or with status 400 (Bad Request) if the paperDTO is not valid,
     * or with status 500 (Internal Server Error) if the paperDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/paper")
    @Timed
    public ResponseEntity<PaperDTO> updatePaper(@RequestBody PaperDTO paperDTO) throws URISyntaxException {
        log.debug("REST request to update Paper : {}", paperDTO);
        if (paperDTO.getId() == null) {
            return createPaper(paperDTO);
        }
        PaperDTO result = paperService.save(paperDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paperDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rfb-event-attendances : get all the rfbEventAttendances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rfbEventAttendances in body
     */
    @GetMapping("/paper")
    @Timed
    public ResponseEntity<List<PaperDTO>> getAllPapers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Papers");
        Page<PaperDTO> page = paperService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rfb-event-attendances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /paper/:id : get the "id" paper.
     *
     * @param id the id of the paperDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rfbEventAttendanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/paper/{id}")
    @Timed
    public ResponseEntity<PaperDTO> getPaper(@PathVariable Long id) {
        log.debug("REST request to get Paper : {}", id);
        PaperDTO paperDTO = paperService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(paperDTO));
    }

    /**
     * DELETE  /paper/:id : delete the "id" paper.
     *
     * @param id the id of the paperDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/paper/{id}")
    @Timed
    public ResponseEntity<Void> deletePaper(@PathVariable Long id) {
        log.debug("REST request to delete Paper : {}", id);
        paperService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
