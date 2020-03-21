import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CovidShowUpTestModule } from '../../../test.module';
import { PatientCaseDetailComponent } from 'app/entities/patient-case/patient-case-detail.component';
import { PatientCase } from 'app/shared/model/patient-case.model';

describe('Component Tests', () => {
  describe('PatientCase Management Detail Component', () => {
    let comp: PatientCaseDetailComponent;
    let fixture: ComponentFixture<PatientCaseDetailComponent>;
    const route = ({ data: of({ patientCase: new PatientCase(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CovidShowUpTestModule],
        declarations: [PatientCaseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PatientCaseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PatientCaseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load patientCase on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.patientCase).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
