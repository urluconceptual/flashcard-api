import {Injectable} from "@angular/core";
import {UserCredentials} from "../models/user-credentials";
import {UserRestService} from "./user-rest.service";
import {MessageService} from "primeng/api";
import {AuthResponse} from "../models/auth-response";
import {Router} from "@angular/router";
import {User} from "../models/user";

@Injectable({providedIn: "root"})
export class AuthenticationService {
  constructor(private userRestService: UserRestService,
              private router: Router) {
    this.checkToken();
  }

  private _userData?: User;

  public get userData(): User | undefined {
    return this._userData;
  }

  private _isSignIn = false;

  public get isSignIn(): boolean {
    return this._isSignIn;
  }

  public set isSignIn(val: boolean) {
    this._isSignIn = val;
  }

  private _isAuthenticated = false;

  public get isAuthenticated(): boolean {
    return this._isAuthenticated;
  }

  public set isAuthenticated(val: boolean) {
    this._isAuthenticated = val;
  }

  public signIn(userCredentials: UserCredentials, messageService: MessageService): void {
    this.userRestService.signIn(userCredentials).subscribe((authResponse) => {
      messageService.add({severity: 'success', summary: 'Success', detail: 'User signed in successfully!'});
      this.setUpAuthenticatedUser(authResponse);
    });
  }

  public signUp(userCredentials: UserCredentials, messageService: MessageService): void {
    this.userRestService.signUp(userCredentials).subscribe((authResponse) => {
      messageService.add({severity: 'success', summary: 'Success', detail: 'User created successfully!'});
      this.setUpAuthenticatedUser(authResponse);
    });
  }

  public signOut(): void {
    this.isAuthenticated = false;
    localStorage.removeItem('authToken');
    this.router.navigate(['/']);
  }

  private checkToken(): void {
    const token = localStorage.getItem('authToken');
    if (!token) return;
    this.userRestService.checkToken().subscribe((authResponse) => {
      this.setUpAuthenticatedUser(authResponse);
    }, () => {
      this.signOut();
    });
  }

  private setUpAuthenticatedUser(authResponse: AuthResponse): void {
    this.isAuthenticated = true;
    this._userData = authResponse.user;
    localStorage.setItem('authToken', authResponse.token);
    this.router.navigate(['/collections']);
  }
}
