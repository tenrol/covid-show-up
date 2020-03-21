import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPatientCase } from 'app/shared/model/patient-case.model';

@Component({
  selector: 'jhi-patient-case-detail',
  templateUrl: './patient-case-detail.component.html'
})
export class PatientCaseDetailComponent implements OnInit {
  patientCase: IPatientCase | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patientCase }) => (this.patientCase = patientCase));
  }

  previousState(): void {
    window.history.back();
  }
}
