import {Component} from '@angular/core';
import {DataView} from "primeng/dataview";
import {FlashcardCollectionComponent} from "../flashcard-collection/flashcard-collection.component";
import {NgForOf} from "@angular/common";
import {MatGridListModule} from "@angular/material/grid-list";
import {Button} from "primeng/button";
import {RouterLink} from "@angular/router";
import {FlashcardService} from "../../services/flashcard.service";
import {Collection} from "../../models/collection";
import {CollectionService} from "../../services/collection.service";
import {Card} from "primeng/card";

@Component({
  selector: 'app-collection-gallery',
  standalone: true,
  imports: [
    DataView,
    FlashcardCollectionComponent,
    NgForOf,
    MatGridListModule,
    Button,
    RouterLink,
    Card
  ],
  templateUrl: './collection-gallery.component.html',
  styleUrl: './collection-gallery.component.scss'
})
export class CollectionGalleryComponent {
  public collections: Collection[] = [];

  constructor(private collectionService: CollectionService) {
    collectionService.initService();
    this.collectionService.getCollections$().subscribe((collections) => {
      this.collections = collections;
    });
  }
}
