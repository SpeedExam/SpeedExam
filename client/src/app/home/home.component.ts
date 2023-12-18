import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HeaderComponent} from "../header/header.component";
import {HeroComponent} from "../hero/hero.component";
import {FeatureComponent} from "../feature/feature.component";
import {FooterComponent} from "../footer/footer.component";
import {TeamComponent} from "../team/team.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, HeaderComponent, HeroComponent, FeatureComponent, FooterComponent, TeamComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
