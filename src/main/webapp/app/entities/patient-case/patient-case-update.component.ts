import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPatientCase, PatientCase } from 'app/shared/model/patient-case.model';
import { PatientCaseService } from './patient-case.service';

@Component({
  selector: 'jhi-patient-case-update',
  templateUrl: './patient-case-update.component.html'
})
export class PatientCaseUpdateComponent implements OnInit {
  isSaving = false;
  sickDateDp: any;
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    patientId: [],
    country: [null, [Validators.required]],
    department: [null, [Validators.required]],
    zipCode: [null, [Validators.required]],
    sickDate: [null, [Validators.required]],
    date: [null, [Validators.required]],
    cough: [],
    fever: [],
    tiredness: [],
    difficultyBreathing: [],
    cardiovascularDisease: [],
    diabetes: [],
    chronicObstructivePulmonaryDisease: [],
    cancer: [],
    hypertension: [],
    age: [null, [Validators.required]],
    sex: [null, [Validators.required]],
    numberOfFlatMates: [null, [Validators.required]],
    testDone: []
  });

  constructor(protected patientCaseService: PatientCaseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patientCase }) => {
      this.updateForm(patientCase);
    });
  }

  updateForm(patientCase: IPatientCase): void {
    this.editForm.patchValue({
      id: patientCase.id,
      patientId: patientCase.patientId,
      country: patientCase.country,
      department: patientCase.department,
      zipCode: patientCase.zipCode,
      sickDate: patientCase.sickDate,
      date: patientCase.date,
      cough: patientCase.cough,
      fever: patientCase.fever,
      tiredness: patientCase.tiredness,
      difficultyBreathing: patientCase.difficultyBreathing,
      cardiovascularDisease: patientCase.cardiovascularDisease,
      diabetes: patientCase.diabetes,
      chronicObstructivePulmonaryDisease: patientCase.chronicObstructivePulmonaryDisease,
      cancer: patientCase.cancer,
      hypertension: patientCase.hypertension,
      age: patientCase.age,
      sex: patientCase.sex,
      numberOfFlatMates: patientCase.numberOfFlatMates,
      testDone: patientCase.testDone
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const patientCase = this.createFromForm();
    if (patientCase.id !== undefined) {
      this.subscribeToSaveResponse(this.patientCaseService.update(patientCase));
    } else {
      this.subscribeToSaveResponse(this.patientCaseService.create(patientCase));
    }
  }

  private createFromForm(): IPatientCase {
    return {
      ...new PatientCase(),
      id: this.editForm.get(['id'])!.value,
      patientId: this.editForm.get(['patientId'])!.value,
      country: this.editForm.get(['country'])!.value,
      department: this.editForm.get(['department'])!.value,
      zipCode: this.editForm.get(['zipCode'])!.value,
      sickDate: this.editForm.get(['sickDate'])!.value,
      date: this.editForm.get(['date'])!.value,
      cough: this.editForm.get(['cough'])!.value,
      fever: this.editForm.get(['fever'])!.value,
      tiredness: this.editForm.get(['tiredness'])!.value,
      difficultyBreathing: this.editForm.get(['difficultyBreathing'])!.value,
      cardiovascularDisease: this.editForm.get(['cardiovascularDisease'])!.value,
      diabetes: this.editForm.get(['diabetes'])!.value,
      chronicObstructivePulmonaryDisease: this.editForm.get(['chronicObstructivePulmonaryDisease'])!.value,
      cancer: this.editForm.get(['cancer'])!.value,
      hypertension: this.editForm.get(['hypertension'])!.value,
      age: this.editForm.get(['age'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      numberOfFlatMates: this.editForm.get(['numberOfFlatMates'])!.value,
      testDone: this.editForm.get(['testDone'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatientCase>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
