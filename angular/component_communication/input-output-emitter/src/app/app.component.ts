import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {

  products = [];
  title = 'Products';
  productToUpdate: any;

  ngOnInit() {
      this.products = this.populateProducts();
  }

  populateProducts() {
      return [
          { 'id': '1', 'title': 'Women\'s Thermal Peplum', 'price': 400, 'amount': 11 },
          { 'id': '2', 'title': 'Women\'s Ruffle Edge Peasant Top', 'price': 200, 'amount': 5 },
          { 'id': '3', 'title': 'Women\'s Lace Neck Blouse', 'price': 78, 'amount': 45 },
          { 'id': '4', 'title': 'Women\'s S Ditsy floral Print Blouse', 'price': 20000, 'amount': 1 },
          { 'id': '5', 'title': 'Women\'s Open Front Cardigan', 'price': 62, 'amount': 15 },
      ];
  }
  
  changeAmount(p) {
      this.productToUpdate = this.products.find(this.findProducts, [p.id]);
      this.productToUpdate.amount = this.productToUpdate.amount + p.updatedAmount;
  }

  findProducts(p) {
      return p.id === this[0];
  }
}
