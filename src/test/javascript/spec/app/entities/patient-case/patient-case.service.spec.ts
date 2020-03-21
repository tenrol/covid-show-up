import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { PatientCaseService } from 'app/entities/patient-case/patient-case.service';
import { IPatientCase, PatientCase } from 'app/shared/model/patient-case.model';
import { Country } from 'app/shared/model/enumerations/country.model';
import { Department } from 'app/shared/model/enumerations/department.model';
import { Sex } from 'app/shared/model/enumerations/sex.model';

describe('Service Tests', () => {
  describe('PatientCase Service', () => {
    let injector: TestBed;
    let service: PatientCaseService;
    let httpMock: HttpTestingController;
    let elemDefault: IPatientCase;
    let expectedResult: IPatientCase | IPatientCase[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PatientCaseService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PatientCase(
        0,
        'AAAAAAA',
        Country.FRANCE,
        Department.HAUT_RHIN,
        0,
        currentDate,
        currentDate,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        0,
        Sex.MALE,
        0,
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            sickDate: currentDate.format(DATE_FORMAT),
            date: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PatientCase', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            sickDate: currentDate.format(DATE_FORMAT),
            date: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            sickDate: currentDate,
            date: currentDate
          },
          returnedFromService
        );

        service.create(new PatientCase()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PatientCase', () => {
        const returnedFromService = Object.assign(
          {
            patientId: 'BBBBBB',
            country: 'BBBBBB',
            department: 'BBBBBB',
            zipCode: 1,
            sickDate: currentDate.format(DATE_FORMAT),
            date: currentDate.format(DATE_FORMAT),
            cough: true,
            fever: true,
            tiredness: true,
            difficultyBreathing: true,
            cardiovascularDisease: true,
            diabetes: true,
            chronicObstructivePulmonaryDisease: true,
            cancer: true,
            hypertension: true,
            age: 1,
            sex: 'BBBBBB',
            numberOfFlatMates: 1,
            testDone: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            sickDate: currentDate,
            date: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PatientCase', () => {
        const returnedFromService = Object.assign(
          {
            patientId: 'BBBBBB',
            country: 'BBBBBB',
            department: 'BBBBBB',
            zipCode: 1,
            sickDate: currentDate.format(DATE_FORMAT),
            date: currentDate.format(DATE_FORMAT),
            cough: true,
            fever: true,
            tiredness: true,
            difficultyBreathing: true,
            cardiovascularDisease: true,
            diabetes: true,
            chronicObstructivePulmonaryDisease: true,
            cancer: true,
            hypertension: true,
            age: 1,
            sex: 'BBBBBB',
            numberOfFlatMates: 1,
            testDone: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            sickDate: currentDate,
            date: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a PatientCase', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
