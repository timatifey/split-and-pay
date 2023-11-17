import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

import {Observable} from 'rxjs';
import {environment} from "../../environments/environment";
import {StorageService} from "../service/user-storage/storage.service";

const USER_ID_HEADER_KEY = 'userId';       // for Spring Boot back-end

@Injectable()
export class HeaderInterceptor implements HttpInterceptor {
  constructor(private storage: StorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const userId = this.storage.getUserId();
    console.log("process set token")
    if (userId != null && req.url != 'api/user/') {
      console.log("setUserId")
      authReq = req.clone(
        {
          headers:
            req.headers
              .set(USER_ID_HEADER_KEY, userId)
        }
      );
    }
    authReq = authReq.clone({url: environment.baseUrl + req.url});
    return next.handle(authReq);
  }


}
