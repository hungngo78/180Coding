import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute  } from '@angular/router';

import { DummyService } from './../services/dummy.service';
import { IProduct } from './../defines/product.interface';

@Component({
	moduleId: module.id,
	selector: 'abc-product-detail',
	template: `
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Product Detail</h3>
			</div>
			<div class="panel-body">
				<h4><span class="label label-info">ID</span>{{product.id}}</h4>
				<h4><span class="label label-info">Name</span>{{product.name}}</h4>
				<h4><span class="label label-info">Description</span>{{product.description}}</h4>
			</div>
			<div class="panel-footer">
				<button (click)="goProductList()" type="button" class="btn btn-danger">Back to Product List</button>
			</div>
		</div>
	`
})

export class ProductDetailComponent implements OnInit {
	product: IProduct;

	constructor(
      private _productService: DummyService,
      private _routerService: Router,
      private _activatedRoutedService: ActivatedRoute
	) {
	}

	goProductList(){
    this._routerService.navigate(['/products']);
	}

	ngOnInit(){
      //let id: string = this._activatedRoutedService.snapshot.params['id'];
      //this.product = this._productService.getProductByID(id);

      /* subscribe changes in route param to reload component  */
      this._activatedRoutedService.params.subscribe(routeParams => {
        this.product = this._productService.getProductByID(routeParams[`id`]);
      });
	}

}
