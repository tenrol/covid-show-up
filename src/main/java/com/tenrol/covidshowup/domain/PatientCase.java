package com.tenrol.covidshowup.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.UUID;

import com.tenrol.covidshowup.domain.enumeration.Country;

import com.tenrol.covidshowup.domain.enumeration.Department;

import com.tenrol.covidshowup.domain.enumeration.Sex;

/**
 * Case entity.\n@author Lorent.
 */
@ApiModel(description = "Case entity.\n@author Lorent.")
@Entity
@Table(name = "patient_case")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PatientCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "patient_id", length = 36)
    private UUID patientId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country country;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    private Department department;

    @NotNull
    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;

    @NotNull
    @Column(name = "sick_date", nullable = false)
    private LocalDate sickDate;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "cough")
    private Boolean cough;

    @Column(name = "fever")
    private Boolean fever;

    @Column(name = "tiredness")
    private Boolean tiredness;

    @Column(name = "difficulty_breathing")
    private Boolean difficultyBreathing;

    @Column(name = "cardiovascular_disease")
    private Boolean cardiovascularDisease;

    @Column(name = "diabetes")
    private Boolean diabetes;

    @Column(name = "chronic_obstructive_pulmonary_disease")
    private Boolean chronicObstructivePulmonaryDisease;

    @Column(name = "cancer")
    private Boolean cancer;

    @Column(name = "hypertension")
    private Boolean hypertension;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Column(name = "number_of_flat_mates", nullable = false)
    private Integer numberOfFlatMates;

    @Column(name = "test_done")
    private Boolean testDone;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public PatientCase patientId(UUID patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public Country getCountry() {
        return country;
    }

    public PatientCase country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Department getDepartment() {
        return department;
    }

    public PatientCase department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public PatientCase zipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getSickDate() {
        return sickDate;
    }

    public PatientCase sickDate(LocalDate sickDate) {
        this.sickDate = sickDate;
        return this;
    }

    public void setSickDate(LocalDate sickDate) {
        this.sickDate = sickDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public PatientCase date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isCough() {
        return cough;
    }

    public PatientCase cough(Boolean cough) {
        this.cough = cough;
        return this;
    }

    public void setCough(Boolean cough) {
        this.cough = cough;
    }

    public Boolean isFever() {
        return fever;
    }

    public PatientCase fever(Boolean fever) {
        this.fever = fever;
        return this;
    }

    public void setFever(Boolean fever) {
        this.fever = fever;
    }

    public Boolean isTiredness() {
        return tiredness;
    }

    public PatientCase tiredness(Boolean tiredness) {
        this.tiredness = tiredness;
        return this;
    }

    public void setTiredness(Boolean tiredness) {
        this.tiredness = tiredness;
    }

    public Boolean isDifficultyBreathing() {
        return difficultyBreathing;
    }

    public PatientCase difficultyBreathing(Boolean difficultyBreathing) {
        this.difficultyBreathing = difficultyBreathing;
        return this;
    }

    public void setDifficultyBreathing(Boolean difficultyBreathing) {
        this.difficultyBreathing = difficultyBreathing;
    }

    public Boolean isCardiovascularDisease() {
        return cardiovascularDisease;
    }

    public PatientCase cardiovascularDisease(Boolean cardiovascularDisease) {
        this.cardiovascularDisease = cardiovascularDisease;
        return this;
    }

    public void setCardiovascularDisease(Boolean cardiovascularDisease) {
        this.cardiovascularDisease = cardiovascularDisease;
    }

    public Boolean isDiabetes() {
        return diabetes;
    }

    public PatientCase diabetes(Boolean diabetes) {
        this.diabetes = diabetes;
        return this;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Boolean isChronicObstructivePulmonaryDisease() {
        return chronicObstructivePulmonaryDisease;
    }

    public PatientCase chronicObstructivePulmonaryDisease(Boolean chronicObstructivePulmonaryDisease) {
        this.chronicObstructivePulmonaryDisease = chronicObstructivePulmonaryDisease;
        return this;
    }

    public void setChronicObstructivePulmonaryDisease(Boolean chronicObstructivePulmonaryDisease) {
        this.chronicObstructivePulmonaryDisease = chronicObstructivePulmonaryDisease;
    }

    public Boolean isCancer() {
        return cancer;
    }

    public PatientCase cancer(Boolean cancer) {
        this.cancer = cancer;
        return this;
    }

    public void setCancer(Boolean cancer) {
        this.cancer = cancer;
    }

    public Boolean isHypertension() {
        return hypertension;
    }

    public PatientCase hypertension(Boolean hypertension) {
        this.hypertension = hypertension;
        return this;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Integer getAge() {
        return age;
    }

    public PatientCase age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public PatientCase sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getNumberOfFlatMates() {
        return numberOfFlatMates;
    }

    public PatientCase numberOfFlatMates(Integer numberOfFlatMates) {
        this.numberOfFlatMates = numberOfFlatMates;
        return this;
    }

    public void setNumberOfFlatMates(Integer numberOfFlatMates) {
        this.numberOfFlatMates = numberOfFlatMates;
    }

    public Boolean isTestDone() {
        return testDone;
    }

    public PatientCase testDone(Boolean testDone) {
        this.testDone = testDone;
        return this;
    }

    public void setTestDone(Boolean testDone) {
        this.testDone = testDone;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientCase)) {
            return false;
        }
        return id != null && id.equals(((PatientCase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PatientCase{" +
            "id=" + getId() +
            ", patientId='" + getPatientId() + "'" +
            ", country='" + getCountry() + "'" +
            ", department='" + getDepartment() + "'" +
            ", zipCode=" + getZipCode() +
            ", sickDate='" + getSickDate() + "'" +
            ", date='" + getDate() + "'" +
            ", cough='" + isCough() + "'" +
            ", fever='" + isFever() + "'" +
            ", tiredness='" + isTiredness() + "'" +
            ", difficultyBreathing='" + isDifficultyBreathing() + "'" +
            ", cardiovascularDisease='" + isCardiovascularDisease() + "'" +
            ", diabetes='" + isDiabetes() + "'" +
            ", chronicObstructivePulmonaryDisease='" + isChronicObstructivePulmonaryDisease() + "'" +
            ", cancer='" + isCancer() + "'" +
            ", hypertension='" + isHypertension() + "'" +
            ", age=" + getAge() +
            ", sex='" + getSex() + "'" +
            ", numberOfFlatMates=" + getNumberOfFlatMates() +
            ", testDone='" + isTestDone() + "'" +
            "}";
    }
}
