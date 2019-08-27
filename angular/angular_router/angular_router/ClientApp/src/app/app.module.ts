import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';

import { HoneComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { NotFoundComponent } from './not_found/not-found.component';

import { AppRoutingModule } from './app-routing.module';

import { ProductListComponent } from './products/product-list.component';
import { ProductDetailComponent } from './products/product-detail.component';

import { DummyService } from './services/dummy.service';


@NgModule({
  declarations: [
    AppComponent,

    HoneComponent,
    ContactComponent,
    AboutComponent,
    NotFoundComponent,

    ProductListComponent,
    ProductDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [DummyService],
  bootstrap: [AppComponent]
})

export class AppModule { }
