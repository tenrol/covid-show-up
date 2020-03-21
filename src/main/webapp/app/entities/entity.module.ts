import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'patient-case',
        loadChildren: () => import('./patient-case/patient-case.module').then(m => m.CovidShowUpPatientCaseModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class CovidShowUpEntityModule {}
