import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPatientCase } from 'app/shared/model/patient-case.model';
import { PatientCaseService } from './patient-case.service';

@Component({
  templateUrl: './patient-case-delete-dialog.component.html'
})
export class PatientCaseDeleteDialogComponent {
  patientCase?: IPatientCase;

  constructor(
    protected patientCaseService: PatientCaseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.patientCaseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('patientCaseListModification');
      this.activeModal.close();
    });
  }
}
