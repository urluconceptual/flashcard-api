import {Injectable} from "@angular/core";
import {Collection} from "../models/collection";
import {Observable} from "rxjs";
import {Rest} from "../config/rest.enum";
import {HttpClient} from "@angular/common/http";
import {Flashcard} from "../models/flashcard";

@Injectable({providedIn: "root"})
export class FlashcardRestService {
  constructor(private httpClient: HttpClient) {
  }

  public updateFlashcardsOfCollection(flashcards: Flashcard[]): Observable<Flashcard> {
    return this.httpClient.post<Flashcard>(Rest.API + Rest.FLASHCARD_API + '/bulk', flashcards);
  }
}
