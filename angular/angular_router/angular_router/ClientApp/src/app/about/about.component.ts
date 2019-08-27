import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'zvn-project-one-about',
  templateUrl: './about.component.html',
})
export class AboutComponent {
  constructor(private router: Router){

  }

  goHome() {
    this.router.navigate(['/']);
  }

  goContact() {
    this.router.navigate(['contact']);
  }

}
