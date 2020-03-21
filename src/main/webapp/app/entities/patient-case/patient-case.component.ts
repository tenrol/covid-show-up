import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPatientCase } from 'app/shared/model/patient-case.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PatientCaseService } from './patient-case.service';
import { PatientCaseDeleteDialogComponent } from './patient-case-delete-dialog.component';

@Component({
  selector: 'jhi-patient-case',
  templateUrl: './patient-case.component.html'
})
export class PatientCaseComponent implements OnInit, OnDestroy {
  patientCases: IPatientCase[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected patientCaseService: PatientCaseService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.patientCases = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.patientCaseService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IPatientCase[]>) => this.paginatePatientCases(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.patientCases = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPatientCases();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPatientCase): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPatientCases(): void {
    this.eventSubscriber = this.eventManager.subscribe('patientCaseListModification', () => this.reset());
  }

  delete(patientCase: IPatientCase): void {
    const modalRef = this.modalService.open(PatientCaseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.patientCase = patientCase;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePatientCases(data: IPatientCase[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.patientCases.push(data[i]);
      }
    }
  }
}
