package com.tenrol.covidshowup.repository;

import com.tenrol.covidshowup.domain.PatientCase;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PatientCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientCaseRepository extends JpaRepository<PatientCase, Long> {
}
