import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CovidShowUpSharedModule } from 'app/shared/shared.module';
import { PatientCaseComponent } from './patient-case.component';
import { PatientCaseDetailComponent } from './patient-case-detail.component';
import { PatientCaseUpdateComponent } from './patient-case-update.component';
import { PatientCaseDeleteDialogComponent } from './patient-case-delete-dialog.component';
import { patientCaseRoute } from './patient-case.route';

@NgModule({
  imports: [CovidShowUpSharedModule, RouterModule.forChild(patientCaseRoute)],
  declarations: [PatientCaseComponent, PatientCaseDetailComponent, PatientCaseUpdateComponent, PatientCaseDeleteDialogComponent],
  entryComponents: [PatientCaseDeleteDialogComponent]
})
export class CovidShowUpPatientCaseModule {}
