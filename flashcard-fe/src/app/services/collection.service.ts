import {Injectable} from "@angular/core";
import {Collection} from "../models/collection";
import {CollectionRestService} from "./collection-rest.service";
import {MessageService} from "primeng/api";
import {BehaviorSubject, map, Observable} from "rxjs";

@Injectable({providedIn: "root"})
export class CollectionService {
  private collections = new Map<number, Collection>();
  private collectionsSubject = new BehaviorSubject<Map<number, Collection>>(this.collections);
  public collections$ = this.collectionsSubject.asObservable();

  private initializedSubject = new BehaviorSubject<boolean>(false);
  public initialized$ = this.initializedSubject.asObservable();

  constructor(private collectionRestService: CollectionRestService) {
    this.collectionRestService.getCollections().subscribe((collections) => {
      this.collections = new Map<number, Collection>(collections.map((collection) => [collection.collectionId, collection] as [number, Collection]));
    });
  }

  public initService(): void {
    this.collectionRestService.getCollections().subscribe((collections) => {
      this.collections = new Map<number, Collection>(collections.map((collection) => [collection.collectionId, collection] as [number, Collection]));
      this.collectionsSubject.next(this.collections);
      this.initializedSubject.next(true);
    });
  }

  public createCollection(collection: Collection, messageService: MessageService): void {
    this.collectionRestService.createCollection(collection).subscribe(() => {
      messageService.add({severity: 'success', summary: 'Success', detail: 'Collection created successfully!'});
      this.collections.set(collection.collectionId, collection);
      this.collectionsSubject.next(this.collections);
    });
  }

  public getCollections$(): Observable<Collection[]> {
    return this.collections$.pipe(map(() => Array.from(this.collections.values())));
  }

  public getCollectionById(id: number): Collection | undefined {
    return this.collections.get(id);
  }

}
