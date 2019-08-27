import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HoneComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { NotFoundComponent } from './not_found/not-found.component';

import { ProductListComponent } from './products/product-list.component';
import { ProductDetailComponent } from './products/product-detail.component';


const appRoutes: Routes = [
  { path: '', redirectTo: '/products', pathMatch: 'full'},
  { path: 'home',         component: HoneComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'product/:id', component: ProductDetailComponent },
  { path: 'contact',      component: ContactComponent },
  { path: 'about',        component: AboutComponent },
  { path: 'about/web',    component: AboutComponent },
  { path: '**',           component: NotFoundComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(appRoutes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
