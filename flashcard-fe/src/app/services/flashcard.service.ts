import {Injectable} from "@angular/core";
import {Collection} from "../models/collection";
import {MessageService} from "primeng/api";
import {Flashcard} from "../models/flashcard";
import {FlashcardRestService} from "./flashcard-rest.service";

@Injectable({providedIn: "root"})
export class FlashcardService {
  constructor(private flashcardRestService: FlashcardRestService) {
  }

  public updateFlashcardsOfCollection(flashcards: Flashcard[], messageService: MessageService): void {
    this.flashcardRestService.updateFlashcardsOfCollection(flashcards).subscribe(() => {
      messageService.add({severity: 'success', summary: 'Success', detail: 'Flashcards edited successfully!'});
    });
  }
}
