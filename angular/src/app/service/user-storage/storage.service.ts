import { Injectable } from '@angular/core';

const USER_ID = 'user-id';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveUserId(userId: string): void {
    window.sessionStorage.removeItem(USER_ID);
    window.sessionStorage.setItem(USER_ID, userId);
  }

  public getUserId(): string | null {
    let item = window.sessionStorage.getItem(USER_ID);
    console.log(item)
    return item;
  }

}