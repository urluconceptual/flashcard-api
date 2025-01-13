import {Injectable} from "@angular/core";
import {Collection} from "../models/collection";
import {Observable} from "rxjs";
import {Rest} from "../config/rest.enum";
import {HttpClient} from "@angular/common/http";

@Injectable({providedIn: "root"})
export class CollectionRestService {
  constructor(private httpClient: HttpClient) {
  }
  public createCollection(collection: Collection): Observable<void> {
    return this.httpClient.post<void>(Rest.API + Rest.COLLECTION_API, collection);
  }

  public getCollections(): Observable<Collection[]> {
    return this.httpClient.get<Collection[]>(Rest.API + Rest.COLLECTION_API);
  }

}
