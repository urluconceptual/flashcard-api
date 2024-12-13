import {Component} from '@angular/core';
import {DataView} from "primeng/dataview";
import {FlashcardCollectionComponent} from "../flashcard-collection/flashcard-collection.component";
import {NgForOf} from "@angular/common";
import {MatGridListModule} from "@angular/material/grid-list";
import {Button} from "primeng/button";
import {RouterLink} from "@angular/router";
import {FlashcardService} from "../../services/flashcard.service";
import {FlashcardCollection} from "../../models/flashcard-collection";

@Component({
  selector: 'app-collection-gallery',
  standalone: true,
  imports: [
    DataView,
    FlashcardCollectionComponent,
    NgForOf,
    MatGridListModule,
    Button,
    RouterLink
  ],
  templateUrl: './collection-gallery.component.html',
  styleUrl: './collection-gallery.component.scss'
})
export class CollectionGalleryComponent {
  public collections: FlashcardCollection[];

  constructor(private flashcardService: FlashcardService) {
    this.collections = this.flashcardService.getCollections();
  }
}
