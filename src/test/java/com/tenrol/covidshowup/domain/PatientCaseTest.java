package com.tenrol.covidshowup.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tenrol.covidshowup.web.rest.TestUtil;

public class PatientCaseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientCase.class);
        PatientCase patientCase1 = new PatientCase();
        patientCase1.setId(1L);
        PatientCase patientCase2 = new PatientCase();
        patientCase2.setId(patientCase1.getId());
        assertThat(patientCase1).isEqualTo(patientCase2);
        patientCase2.setId(2L);
        assertThat(patientCase1).isNotEqualTo(patientCase2);
        patientCase1.setId(null);
        assertThat(patientCase1).isNotEqualTo(patientCase2);
    }
}
