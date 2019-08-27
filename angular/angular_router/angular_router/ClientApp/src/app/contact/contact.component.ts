import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'zvn-project-one-contact',
  templateUrl: './contact.component.html',
})
export class ContactComponent {
  constructor(private router: Router){

  }

  goHome() {
    this.router.navigate(['/']);
  }

  goAbout() {
    this.router.navigate(['about']);
  }
}
