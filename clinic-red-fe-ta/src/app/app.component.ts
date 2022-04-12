import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'clinic-red-fe-ta';

  isOnline: boolean;
  constructor(
  ) {
    this.isOnline = navigator.onLine;
  }
}
