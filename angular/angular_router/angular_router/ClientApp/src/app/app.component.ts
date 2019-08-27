import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { IProduct } from './defines/product.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(
    private _routerService: Router
  ) {

  }
   
  onViewProduct(productID: IProduct) {
    this._routerService.navigate(['/product', productID]);
  }
}
