import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CovidShowUpTestModule } from '../../../test.module';
import { PatientCaseUpdateComponent } from 'app/entities/patient-case/patient-case-update.component';
import { PatientCaseService } from 'app/entities/patient-case/patient-case.service';
import { PatientCase } from 'app/shared/model/patient-case.model';

describe('Component Tests', () => {
  describe('PatientCase Management Update Component', () => {
    let comp: PatientCaseUpdateComponent;
    let fixture: ComponentFixture<PatientCaseUpdateComponent>;
    let service: PatientCaseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CovidShowUpTestModule],
        declarations: [PatientCaseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PatientCaseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PatientCaseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PatientCaseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PatientCase(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PatientCase();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
