import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.scss'
})
export class OverviewComponent {
  constructor(private route:Router) {
  }
onClick(){
    this.route.navigate(["/prof"]);
}
}
