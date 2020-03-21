package com.tenrol.covidshowup.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tenrol.covidshowup.domain.PatientCase;
import com.tenrol.covidshowup.repository.PatientCaseRepository;
import com.tenrol.covidshowup.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.tenrol.covidshowup.domain.PatientCase}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PatientCaseResource {

    private final Logger log = LoggerFactory.getLogger(PatientCaseResource.class);

    private static final String ENTITY_NAME = "patientCase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientCaseRepository patientCaseRepository;

    public PatientCaseResource(PatientCaseRepository patientCaseRepository) {
        this.patientCaseRepository = patientCaseRepository;
    }

    /**
     * {@code POST  /patient-cases} : Create a new patientCase.
     *
     * @param patientCase the patientCase to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientCase, or with status {@code 400 (Bad Request)} if the patientCase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-cases")
    public ResponseEntity<PatientCase> createPatientCase(@Valid @RequestBody PatientCase patientCase) throws URISyntaxException {
        log.debug("REST request to save PatientCase : {}", patientCase);
        if (patientCase.getId() != null) {
            throw new BadRequestAlertException("A new patientCase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PatientCase result = patientCaseRepository.save(patientCase);
        return ResponseEntity.created(new URI("/api/patient-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /patient-cases} : Updates an existing patientCase.
     *
     * @param patientCase the patientCase to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientCase,
     * or with status {@code 400 (Bad Request)} if the patientCase is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientCase couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-cases")
    public ResponseEntity<PatientCase> updatePatientCase(@Valid @RequestBody PatientCase patientCase) throws URISyntaxException {
        log.debug("REST request to update PatientCase : {}", patientCase);
        if (patientCase.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PatientCase result = patientCaseRepository.save(patientCase);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, patientCase.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /patient-cases} : get all the patientCases.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientCases in body.
     */
    @GetMapping("/patient-cases")
    public ResponseEntity<List<PatientCase>> getAllPatientCases(Pageable pageable) {
        log.debug("REST request to get a page of PatientCases");
        Page<PatientCase> page = patientCaseRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /patient-cases/:id} : get the "id" patientCase.
     *
     * @param id the id of the patientCase to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientCase, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-cases/{id}")
    public ResponseEntity<PatientCase> getPatientCase(@PathVariable Long id) {
        log.debug("REST request to get PatientCase : {}", id);
        Optional<PatientCase> patientCase = patientCaseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(patientCase);
    }

    /**
     * {@code DELETE  /patient-cases/:id} : delete the "id" patientCase.
     *
     * @param id the id of the patientCase to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-cases/{id}")
    public ResponseEntity<Void> deletePatientCase(@PathVariable Long id) {
        log.debug("REST request to delete PatientCase : {}", id);
        patientCaseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
