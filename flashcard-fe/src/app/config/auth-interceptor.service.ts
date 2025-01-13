import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor() {
  }

  private get authToken(): string | null {
    return localStorage.getItem('authToken');
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authToken && !req.headers.has('Authorization')) {
      const clonedRequest = req.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authToken}`
        }
      });
      return next.handle(clonedRequest);
    }
    return next.handle(req);
  }
}
