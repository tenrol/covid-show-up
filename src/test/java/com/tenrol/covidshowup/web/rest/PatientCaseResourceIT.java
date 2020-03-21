package com.tenrol.covidshowup.web.rest;

import com.tenrol.covidshowup.CovidShowUpApp;
import com.tenrol.covidshowup.domain.PatientCase;
import com.tenrol.covidshowup.repository.PatientCaseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tenrol.covidshowup.domain.enumeration.Country;
import com.tenrol.covidshowup.domain.enumeration.Department;
import com.tenrol.covidshowup.domain.enumeration.Sex;
/**
 * Integration tests for the {@link PatientCaseResource} REST controller.
 */
@SpringBootTest(classes = CovidShowUpApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PatientCaseResourceIT {

    private static final UUID DEFAULT_PATIENT_ID = UUID.randomUUID();
    private static final UUID UPDATED_PATIENT_ID = UUID.randomUUID();

    private static final Country DEFAULT_COUNTRY = Country.FRANCE;
    private static final Country UPDATED_COUNTRY = Country.FRANCE;

    private static final Department DEFAULT_DEPARTMENT = Department.HAUT_RHIN;
    private static final Department UPDATED_DEPARTMENT = Department.BAS_RHIN;

    private static final Integer DEFAULT_ZIP_CODE = 1;
    private static final Integer UPDATED_ZIP_CODE = 2;

    private static final LocalDate DEFAULT_SICK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SICK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_COUGH = false;
    private static final Boolean UPDATED_COUGH = true;

    private static final Boolean DEFAULT_FEVER = false;
    private static final Boolean UPDATED_FEVER = true;

    private static final Boolean DEFAULT_TIREDNESS = false;
    private static final Boolean UPDATED_TIREDNESS = true;

    private static final Boolean DEFAULT_DIFFICULTY_BREATHING = false;
    private static final Boolean UPDATED_DIFFICULTY_BREATHING = true;

    private static final Boolean DEFAULT_CARDIOVASCULAR_DISEASE = false;
    private static final Boolean UPDATED_CARDIOVASCULAR_DISEASE = true;

    private static final Boolean DEFAULT_DIABETES = false;
    private static final Boolean UPDATED_DIABETES = true;

    private static final Boolean DEFAULT_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE = false;
    private static final Boolean UPDATED_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE = true;

    private static final Boolean DEFAULT_CANCER = false;
    private static final Boolean UPDATED_CANCER = true;

    private static final Boolean DEFAULT_HYPERTENSION = false;
    private static final Boolean UPDATED_HYPERTENSION = true;

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Sex DEFAULT_SEX = Sex.MALE;
    private static final Sex UPDATED_SEX = Sex.FEMALE;

    private static final Integer DEFAULT_NUMBER_OF_FLAT_MATES = 1;
    private static final Integer UPDATED_NUMBER_OF_FLAT_MATES = 2;

    private static final Boolean DEFAULT_TEST_DONE = false;
    private static final Boolean UPDATED_TEST_DONE = true;

    @Autowired
    private PatientCaseRepository patientCaseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientCaseMockMvc;

    private PatientCase patientCase;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientCase createEntity(EntityManager em) {
        PatientCase patientCase = new PatientCase()
            .patientId(DEFAULT_PATIENT_ID)
            .country(DEFAULT_COUNTRY)
            .department(DEFAULT_DEPARTMENT)
            .zipCode(DEFAULT_ZIP_CODE)
            .sickDate(DEFAULT_SICK_DATE)
            .date(DEFAULT_DATE)
            .cough(DEFAULT_COUGH)
            .fever(DEFAULT_FEVER)
            .tiredness(DEFAULT_TIREDNESS)
            .difficultyBreathing(DEFAULT_DIFFICULTY_BREATHING)
            .cardiovascularDisease(DEFAULT_CARDIOVASCULAR_DISEASE)
            .diabetes(DEFAULT_DIABETES)
            .chronicObstructivePulmonaryDisease(DEFAULT_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE)
            .cancer(DEFAULT_CANCER)
            .hypertension(DEFAULT_HYPERTENSION)
            .age(DEFAULT_AGE)
            .sex(DEFAULT_SEX)
            .numberOfFlatMates(DEFAULT_NUMBER_OF_FLAT_MATES)
            .testDone(DEFAULT_TEST_DONE);
        return patientCase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientCase createUpdatedEntity(EntityManager em) {
        PatientCase patientCase = new PatientCase()
            .patientId(UPDATED_PATIENT_ID)
            .country(UPDATED_COUNTRY)
            .department(UPDATED_DEPARTMENT)
            .zipCode(UPDATED_ZIP_CODE)
            .sickDate(UPDATED_SICK_DATE)
            .date(UPDATED_DATE)
            .cough(UPDATED_COUGH)
            .fever(UPDATED_FEVER)
            .tiredness(UPDATED_TIREDNESS)
            .difficultyBreathing(UPDATED_DIFFICULTY_BREATHING)
            .cardiovascularDisease(UPDATED_CARDIOVASCULAR_DISEASE)
            .diabetes(UPDATED_DIABETES)
            .chronicObstructivePulmonaryDisease(UPDATED_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE)
            .cancer(UPDATED_CANCER)
            .hypertension(UPDATED_HYPERTENSION)
            .age(UPDATED_AGE)
            .sex(UPDATED_SEX)
            .numberOfFlatMates(UPDATED_NUMBER_OF_FLAT_MATES)
            .testDone(UPDATED_TEST_DONE);
        return patientCase;
    }

    @BeforeEach
    public void initTest() {
        patientCase = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatientCase() throws Exception {
        int databaseSizeBeforeCreate = patientCaseRepository.findAll().size();

        // Create the PatientCase
        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isCreated());

        // Validate the PatientCase in the database
        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeCreate + 1);
        PatientCase testPatientCase = patientCaseList.get(patientCaseList.size() - 1);
        assertThat(testPatientCase.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testPatientCase.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPatientCase.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testPatientCase.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testPatientCase.getSickDate()).isEqualTo(DEFAULT_SICK_DATE);
        assertThat(testPatientCase.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testPatientCase.isCough()).isEqualTo(DEFAULT_COUGH);
        assertThat(testPatientCase.isFever()).isEqualTo(DEFAULT_FEVER);
        assertThat(testPatientCase.isTiredness()).isEqualTo(DEFAULT_TIREDNESS);
        assertThat(testPatientCase.isDifficultyBreathing()).isEqualTo(DEFAULT_DIFFICULTY_BREATHING);
        assertThat(testPatientCase.isCardiovascularDisease()).isEqualTo(DEFAULT_CARDIOVASCULAR_DISEASE);
        assertThat(testPatientCase.isDiabetes()).isEqualTo(DEFAULT_DIABETES);
        assertThat(testPatientCase.isChronicObstructivePulmonaryDisease()).isEqualTo(DEFAULT_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE);
        assertThat(testPatientCase.isCancer()).isEqualTo(DEFAULT_CANCER);
        assertThat(testPatientCase.isHypertension()).isEqualTo(DEFAULT_HYPERTENSION);
        assertThat(testPatientCase.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testPatientCase.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testPatientCase.getNumberOfFlatMates()).isEqualTo(DEFAULT_NUMBER_OF_FLAT_MATES);
        assertThat(testPatientCase.isTestDone()).isEqualTo(DEFAULT_TEST_DONE);
    }

    @Test
    @Transactional
    public void createPatientCaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientCaseRepository.findAll().size();

        // Create the PatientCase with an existing ID
        patientCase.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        // Validate the PatientCase in the database
        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setCountry(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDepartmentIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setDepartment(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZipCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setZipCode(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSickDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setSickDate(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setDate(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setAge(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setSex(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberOfFlatMatesIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientCaseRepository.findAll().size();
        // set the field null
        patientCase.setNumberOfFlatMates(null);

        // Create the PatientCase, which fails.

        restPatientCaseMockMvc.perform(post("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPatientCases() throws Exception {
        // Initialize the database
        patientCaseRepository.saveAndFlush(patientCase);

        // Get all the patientCaseList
        restPatientCaseMockMvc.perform(get("/api/patient-cases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patientCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT.toString())))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].sickDate").value(hasItem(DEFAULT_SICK_DATE.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].cough").value(hasItem(DEFAULT_COUGH.booleanValue())))
            .andExpect(jsonPath("$.[*].fever").value(hasItem(DEFAULT_FEVER.booleanValue())))
            .andExpect(jsonPath("$.[*].tiredness").value(hasItem(DEFAULT_TIREDNESS.booleanValue())))
            .andExpect(jsonPath("$.[*].difficultyBreathing").value(hasItem(DEFAULT_DIFFICULTY_BREATHING.booleanValue())))
            .andExpect(jsonPath("$.[*].cardiovascularDisease").value(hasItem(DEFAULT_CARDIOVASCULAR_DISEASE.booleanValue())))
            .andExpect(jsonPath("$.[*].diabetes").value(hasItem(DEFAULT_DIABETES.booleanValue())))
            .andExpect(jsonPath("$.[*].chronicObstructivePulmonaryDisease").value(hasItem(DEFAULT_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE.booleanValue())))
            .andExpect(jsonPath("$.[*].cancer").value(hasItem(DEFAULT_CANCER.booleanValue())))
            .andExpect(jsonPath("$.[*].hypertension").value(hasItem(DEFAULT_HYPERTENSION.booleanValue())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].numberOfFlatMates").value(hasItem(DEFAULT_NUMBER_OF_FLAT_MATES)))
            .andExpect(jsonPath("$.[*].testDone").value(hasItem(DEFAULT_TEST_DONE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPatientCase() throws Exception {
        // Initialize the database
        patientCaseRepository.saveAndFlush(patientCase);

        // Get the patientCase
        restPatientCaseMockMvc.perform(get("/api/patient-cases/{id}", patientCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patientCase.getId().intValue()))
            .andExpect(jsonPath("$.patientId").value(DEFAULT_PATIENT_ID.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT.toString()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE))
            .andExpect(jsonPath("$.sickDate").value(DEFAULT_SICK_DATE.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.cough").value(DEFAULT_COUGH.booleanValue()))
            .andExpect(jsonPath("$.fever").value(DEFAULT_FEVER.booleanValue()))
            .andExpect(jsonPath("$.tiredness").value(DEFAULT_TIREDNESS.booleanValue()))
            .andExpect(jsonPath("$.difficultyBreathing").value(DEFAULT_DIFFICULTY_BREATHING.booleanValue()))
            .andExpect(jsonPath("$.cardiovascularDisease").value(DEFAULT_CARDIOVASCULAR_DISEASE.booleanValue()))
            .andExpect(jsonPath("$.diabetes").value(DEFAULT_DIABETES.booleanValue()))
            .andExpect(jsonPath("$.chronicObstructivePulmonaryDisease").value(DEFAULT_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE.booleanValue()))
            .andExpect(jsonPath("$.cancer").value(DEFAULT_CANCER.booleanValue()))
            .andExpect(jsonPath("$.hypertension").value(DEFAULT_HYPERTENSION.booleanValue()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.numberOfFlatMates").value(DEFAULT_NUMBER_OF_FLAT_MATES))
            .andExpect(jsonPath("$.testDone").value(DEFAULT_TEST_DONE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPatientCase() throws Exception {
        // Get the patientCase
        restPatientCaseMockMvc.perform(get("/api/patient-cases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatientCase() throws Exception {
        // Initialize the database
        patientCaseRepository.saveAndFlush(patientCase);

        int databaseSizeBeforeUpdate = patientCaseRepository.findAll().size();

        // Update the patientCase
        PatientCase updatedPatientCase = patientCaseRepository.findById(patientCase.getId()).get();
        // Disconnect from session so that the updates on updatedPatientCase are not directly saved in db
        em.detach(updatedPatientCase);
        updatedPatientCase
            .patientId(UPDATED_PATIENT_ID)
            .country(UPDATED_COUNTRY)
            .department(UPDATED_DEPARTMENT)
            .zipCode(UPDATED_ZIP_CODE)
            .sickDate(UPDATED_SICK_DATE)
            .date(UPDATED_DATE)
            .cough(UPDATED_COUGH)
            .fever(UPDATED_FEVER)
            .tiredness(UPDATED_TIREDNESS)
            .difficultyBreathing(UPDATED_DIFFICULTY_BREATHING)
            .cardiovascularDisease(UPDATED_CARDIOVASCULAR_DISEASE)
            .diabetes(UPDATED_DIABETES)
            .chronicObstructivePulmonaryDisease(UPDATED_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE)
            .cancer(UPDATED_CANCER)
            .hypertension(UPDATED_HYPERTENSION)
            .age(UPDATED_AGE)
            .sex(UPDATED_SEX)
            .numberOfFlatMates(UPDATED_NUMBER_OF_FLAT_MATES)
            .testDone(UPDATED_TEST_DONE);

        restPatientCaseMockMvc.perform(put("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPatientCase)))
            .andExpect(status().isOk());

        // Validate the PatientCase in the database
        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeUpdate);
        PatientCase testPatientCase = patientCaseList.get(patientCaseList.size() - 1);
        assertThat(testPatientCase.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testPatientCase.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPatientCase.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testPatientCase.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testPatientCase.getSickDate()).isEqualTo(UPDATED_SICK_DATE);
        assertThat(testPatientCase.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testPatientCase.isCough()).isEqualTo(UPDATED_COUGH);
        assertThat(testPatientCase.isFever()).isEqualTo(UPDATED_FEVER);
        assertThat(testPatientCase.isTiredness()).isEqualTo(UPDATED_TIREDNESS);
        assertThat(testPatientCase.isDifficultyBreathing()).isEqualTo(UPDATED_DIFFICULTY_BREATHING);
        assertThat(testPatientCase.isCardiovascularDisease()).isEqualTo(UPDATED_CARDIOVASCULAR_DISEASE);
        assertThat(testPatientCase.isDiabetes()).isEqualTo(UPDATED_DIABETES);
        assertThat(testPatientCase.isChronicObstructivePulmonaryDisease()).isEqualTo(UPDATED_CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE);
        assertThat(testPatientCase.isCancer()).isEqualTo(UPDATED_CANCER);
        assertThat(testPatientCase.isHypertension()).isEqualTo(UPDATED_HYPERTENSION);
        assertThat(testPatientCase.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPatientCase.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testPatientCase.getNumberOfFlatMates()).isEqualTo(UPDATED_NUMBER_OF_FLAT_MATES);
        assertThat(testPatientCase.isTestDone()).isEqualTo(UPDATED_TEST_DONE);
    }

    @Test
    @Transactional
    public void updateNonExistingPatientCase() throws Exception {
        int databaseSizeBeforeUpdate = patientCaseRepository.findAll().size();

        // Create the PatientCase

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientCaseMockMvc.perform(put("/api/patient-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientCase)))
            .andExpect(status().isBadRequest());

        // Validate the PatientCase in the database
        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatientCase() throws Exception {
        // Initialize the database
        patientCaseRepository.saveAndFlush(patientCase);

        int databaseSizeBeforeDelete = patientCaseRepository.findAll().size();

        // Delete the patientCase
        restPatientCaseMockMvc.perform(delete("/api/patient-cases/{id}", patientCase.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PatientCase> patientCaseList = patientCaseRepository.findAll();
        assertThat(patientCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
