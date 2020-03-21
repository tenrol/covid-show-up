import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPatientCase } from 'app/shared/model/patient-case.model';

type EntityResponseType = HttpResponse<IPatientCase>;
type EntityArrayResponseType = HttpResponse<IPatientCase[]>;

@Injectable({ providedIn: 'root' })
export class PatientCaseService {
  public resourceUrl = SERVER_API_URL + 'api/patient-cases';

  constructor(protected http: HttpClient) {}

  create(patientCase: IPatientCase): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(patientCase);
    return this.http
      .post<IPatientCase>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(patientCase: IPatientCase): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(patientCase);
    return this.http
      .put<IPatientCase>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPatientCase>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPatientCase[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(patientCase: IPatientCase): IPatientCase {
    const copy: IPatientCase = Object.assign({}, patientCase, {
      sickDate: patientCase.sickDate && patientCase.sickDate.isValid() ? patientCase.sickDate.format(DATE_FORMAT) : undefined,
      date: patientCase.date && patientCase.date.isValid() ? patientCase.date.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.sickDate = res.body.sickDate ? moment(res.body.sickDate) : undefined;
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((patientCase: IPatientCase) => {
        patientCase.sickDate = patientCase.sickDate ? moment(patientCase.sickDate) : undefined;
        patientCase.date = patientCase.date ? moment(patientCase.date) : undefined;
      });
    }
    return res;
  }
}
