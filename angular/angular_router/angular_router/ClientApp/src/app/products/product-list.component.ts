import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { DummyService } from './../services/dummy.service';
import { IProduct } from './../defines/product.interface';

@Component({
	moduleId: module.id,
	selector: 'abc-product-list',
	template: `
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Product List</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>View</th>
						</tr>
					</thead>
					<tbody>
						<tr *ngFor="let product of products; let i = index">
							<td>{{ i+1 }}</td>
							<td>{{ product.name }}</td>
							<td><a (click)="onSelect(product.id)" class="label label-success">View</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	`
})

export class ProductListComponent implements OnInit {
  products: IProduct[];

	constructor(
		private _productService: DummyService,
		private _routerService: Router
	) {

	}

	ngOnInit() {
      this.products = this._productService.getProducts();
  }

  onSelect(productID: IProduct) {
    this._routerService.navigate(['/product', productID]);
	}
}
