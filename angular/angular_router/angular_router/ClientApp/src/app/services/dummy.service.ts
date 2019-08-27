import { Injectable } from '@angular/core';
import { IProduct } from './../defines/product.interface';

@Injectable()
export class DummyService {
  products: IProduct[] = [];

	constructor() {
      this.products = [
        { id: "P01", name: "Love Sadie", description: "Women's Ruffle Asymmetrical Blouse"},
        { id: "P02", name: "No Boundaries", description: "Juniors' Spring Bouquet Flounce Swimsuit Top"},
        { id: "P03", name: "Heart N Crush", description: "Women's Sweater Tank"},
        { id: "P04", name: "Time and Tru", description: "Women's Lace Tank Top"},
        { id: "P05", name: "Melrose Ave", description: "Melrose Ave Women's Call Me Vegan Booties"},
		];
	}

  getProducts(): IProduct[] {
    return this.products;
	}

  getProductByID(id: string): IProduct {
    let result: IProduct = null;
    for (var i = 0; i < this.products.length; i++) {
          if (this.products[i].id == id) {
            result = this.products[i];
				break;
			}
		}
		return result;
	}
}
