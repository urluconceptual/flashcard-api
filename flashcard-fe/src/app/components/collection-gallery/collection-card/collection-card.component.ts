import {Component, Input} from '@angular/core';
import {Flashcard} from "../../../models/flashcard";
import {Card} from "primeng/card";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-collection-card',
  standalone: true,
  imports: [
    Card,
    RouterLink
  ],
  templateUrl: './collection-card.component.html',
  styleUrl: './collection-card.component.scss'
})
export class CollectionCardComponent {

  @Input() collection: Collection;
}
