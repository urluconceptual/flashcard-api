import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { FlashcardComponent } from '../flashcard/flashcard.component';
import {Carousel} from "primeng/carousel";
import {Flashcard} from "../../models/flashcard";
import {FlashcardCollection} from "../../models/flashcard-collection";
import {FlashcardService} from "../../services/flashcard.service";

@Component({
  selector: 'app-flashcard-collection',
  standalone: true,
  imports: [FlashcardComponent, Carousel],
  templateUrl: './flashcard-collection.component.html',
  styleUrl: './flashcard-collection.component.scss',
})
export class FlashcardCollectionComponent implements OnInit {
  @Input() collectionId!: string;

  public collection!: FlashcardCollection;

  constructor(private flashcardService: FlashcardService) {
  }

  ngOnInit() {
    this.collection = this.flashcardService.getCollectionById(this.collectionId)!;
  }
}
