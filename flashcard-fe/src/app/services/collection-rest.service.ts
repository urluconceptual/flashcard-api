import {Injectable} from "@angular/core";
import {Collection} from "../models/collection";
import {Observable} from "rxjs";
import {Rest} from "../config/rest.enum";
import {HttpClient} from "@angular/common/http";
import {Flashcard} from "../models/flashcard";

@Injectable({providedIn: "root"})
export class CollectionRestService {
  constructor(private httpClient: HttpClient) {
  }

  public createCollection(collection: Collection): Observable<void> {
    return this.httpClient.post<void>(Rest.API + Rest.COLLECTION_API, collection);
  }

  public updateCollection(collection: Collection): Observable<void> {
    return this.httpClient.put<void>(Rest.API + Rest.COLLECTION_API + '/' + collection.collectionId, collection);
  }

  public deleteCollection(collection: Collection): Observable<void> {
    return this.httpClient.delete<void>(Rest.API + Rest.COLLECTION_API + '/' + collection.collectionId);
  }

  public getCollections(): Observable<Collection[]> {
    return this.httpClient.get<Collection[]>(Rest.API + Rest.COLLECTION_API);
  }

  public updateFlashcardsOfCollection(collection: Collection, flashcard: Flashcard): Observable<Flashcard> {
    return this.httpClient.put<Flashcard>(Rest.API + Rest.FLASHCARD_API + '/' + collection.collectionId + '/flashcards', flashcard);
  }
}
