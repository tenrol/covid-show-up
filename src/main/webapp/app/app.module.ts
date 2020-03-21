import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CovidShowUpSharedModule } from 'app/shared/shared.module';
import { CovidShowUpCoreModule } from 'app/core/core.module';
import { CovidShowUpAppRoutingModule } from './app-routing.module';
import { CovidShowUpHomeModule } from './home/home.module';
import { CovidShowUpEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CovidShowUpSharedModule,
    CovidShowUpCoreModule,
    CovidShowUpHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CovidShowUpEntityModule,
    CovidShowUpAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class CovidShowUpAppModule {}
