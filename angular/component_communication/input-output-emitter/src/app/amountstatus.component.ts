import { Component, Input, EventEmitter, Output, OnChanges } from '@angular/core';

@Component({
    selector: 'app-amount-status',
    template: `<input type='number' [(ngModel)]='updatedValue'/> 
        <button class='btn btn-primary'
            [class]='class'
            (click)="changeAmount()">Change Amount</button>`
})

export class AmountStatusComponent implements OnChanges {

    @Input() productId: number;
	@Input() amount: number;
	
    @Output() valueFromEmitter = new EventEmitter();
	
    class = '';
    updatedValue: number;

    changeAmount() {
        this.valueFromEmitter.emit({ id: this.productId, updatedAmount: this.updatedValue });
        this.updatedValue = null;
    }

    ngOnChanges() {
        if (this.amount > 10) {
            this.class = 'btn btn-success';
        } else {
            this.class = 'btn btn-danger';
        }
    }
}