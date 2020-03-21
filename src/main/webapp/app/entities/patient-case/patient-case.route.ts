import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPatientCase, PatientCase } from 'app/shared/model/patient-case.model';
import { PatientCaseService } from './patient-case.service';
import { PatientCaseComponent } from './patient-case.component';
import { PatientCaseDetailComponent } from './patient-case-detail.component';
import { PatientCaseUpdateComponent } from './patient-case-update.component';

@Injectable({ providedIn: 'root' })
export class PatientCaseResolve implements Resolve<IPatientCase> {
  constructor(private service: PatientCaseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPatientCase> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((patientCase: HttpResponse<PatientCase>) => {
          if (patientCase.body) {
            return of(patientCase.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PatientCase());
  }
}

export const patientCaseRoute: Routes = [
  {
    path: '',
    component: PatientCaseComponent,
    data: {
      //authorities: [Authority.USER],
      pageTitle: 'PatientCases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PatientCaseDetailComponent,
    resolve: {
      patientCase: PatientCaseResolve
    },
    data: {
      //authorities: [Authority.USER],
      pageTitle: 'PatientCases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PatientCaseUpdateComponent,
    resolve: {
      patientCase: PatientCaseResolve
    },
    data: {
      //authorities: [Authority.USER],
      pageTitle: 'PatientCases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PatientCaseUpdateComponent,
    resolve: {
      patientCase: PatientCaseResolve
    },
    data: {
      //authorities: [Authority.USER],
      pageTitle: 'PatientCases'
    },
    canActivate: [UserRouteAccessService]
  }
];
