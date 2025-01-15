import {Component, Input, OnInit} from '@angular/core';
import {FlashcardComponent} from '../flashcard/flashcard.component';
import {Carousel} from "primeng/carousel";
import {Collection} from "../../models/collection";
import {CollectionService} from "../../services/collection.service";

@Component({
  selector: 'app-flashcard-collection',
  standalone: true,
  imports: [FlashcardComponent, Carousel],
  templateUrl: './flashcard-collection.component.html',
  styleUrl: './flashcard-collection.component.scss',
})
export class FlashcardCollectionComponent implements OnInit {
  @Input() collectionId!: number;

  public collection!: Collection;

  constructor(private collectionService: CollectionService) {
  }

  ngOnInit() {
    this.collection = this.collectionService.getCollectionById(this.collectionId)!;
  }
}
