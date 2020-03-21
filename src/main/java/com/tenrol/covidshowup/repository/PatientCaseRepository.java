package com.tenrol.covidshowup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenrol.covidshowup.domain.PatientCase;

/**
 * Spring Data  repository for the PatientCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientCaseRepository extends JpaRepository<PatientCase, Long> {
}
