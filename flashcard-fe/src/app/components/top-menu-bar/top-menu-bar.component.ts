import { Component } from '@angular/core';
import {Menubar} from "primeng/menubar";

@Component({
  selector: 'app-top-menu-bar',
  standalone: true,
  imports: [
    Menubar
  ],
  templateUrl: './top-menu-bar.component.html',
  styleUrl: './top-menu-bar.component.scss'
})
export class TopMenuBarComponent {

}
