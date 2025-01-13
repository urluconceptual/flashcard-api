import {Injectable} from "@angular/core";
import {UserCredentials} from "../models/user-credentials";
import {HttpClient} from "@angular/common/http";
import {Rest} from "../config/rest.enum";
import {Observable} from "rxjs";
import {AuthResponse} from "../models/auth-response";

@Injectable({providedIn: "root"})
export class UserRestService {
  constructor(private httpClient: HttpClient) {
  }

  public signIn(userCredentials: UserCredentials): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(Rest.API + Rest.LOGIN_API, userCredentials);
  }

  public signUp(userCredentials: UserCredentials): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(Rest.API + Rest.USER_API, userCredentials);
  }

  public checkToken(): Observable<AuthResponse> {
    return this.httpClient.get<AuthResponse>(Rest.API + Rest.CHECK_TOKEN_API);
  }

  public signOut() {
    // ceva
  }

  public getUserData() {
    // ceva
  }

  public updateUserData() {
    // ceva
  }

  public deleteUserData() {
    // ceva
  }
}
