import { Moment } from 'moment';
import { Country } from 'app/shared/model/enumerations/country.model';
import { Department } from 'app/shared/model/enumerations/department.model';
import { Sex } from 'app/shared/model/enumerations/sex.model';

export interface IPatientCase {
  id?: number;
  patientId?: string;
  country?: Country;
  department?: Department;
  zipCode?: number;
  sickDate?: Moment;
  date?: Moment;
  cough?: boolean;
  fever?: boolean;
  tiredness?: boolean;
  difficultyBreathing?: boolean;
  cardiovascularDisease?: boolean;
  diabetes?: boolean;
  chronicObstructivePulmonaryDisease?: boolean;
  cancer?: boolean;
  hypertension?: boolean;
  age?: number;
  sex?: Sex;
  numberOfFlatMates?: number;
  testDone?: boolean;
}

export class PatientCase implements IPatientCase {
  constructor(
    public id?: number,
    public patientId?: string,
    public country?: Country,
    public department?: Department,
    public zipCode?: number,
    public sickDate?: Moment,
    public date?: Moment,
    public cough?: boolean,
    public fever?: boolean,
    public tiredness?: boolean,
    public difficultyBreathing?: boolean,
    public cardiovascularDisease?: boolean,
    public diabetes?: boolean,
    public chronicObstructivePulmonaryDisease?: boolean,
    public cancer?: boolean,
    public hypertension?: boolean,
    public age?: number,
    public sex?: Sex,
    public numberOfFlatMates?: number,
    public testDone?: boolean
  ) {
    this.cough = this.cough || false;
    this.fever = this.fever || false;
    this.tiredness = this.tiredness || false;
    this.difficultyBreathing = this.difficultyBreathing || false;
    this.cardiovascularDisease = this.cardiovascularDisease || false;
    this.diabetes = this.diabetes || false;
    this.chronicObstructivePulmonaryDisease = this.chronicObstructivePulmonaryDisease || false;
    this.cancer = this.cancer || false;
    this.hypertension = this.hypertension || false;
    this.testDone = this.testDone || false;
  }
}
