import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Room} from "./dto/room";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) {
  }

  getRooms(): Observable<Room[]> {
    console.log("get Rooms")
    return this.http.get<Room[]>('api/rooms/', httpOptions);
  }

  getRoom(id?: number): Observable<Room> {
    console.log("get Room")
    return this.http.get<Room>('api/rooms/' + id, httpOptions);
  }

  createRoom(value: any): Observable<Room> {
    console.log("create Room")
    return this.http.post<Room>('api/rooms/', {
      name: value.roomName
    }, httpOptions);
  }

  incomeRoom(value: any): Observable<Room> {
    console.log("income Room")
    return this.http.get<Room>('api/rooms/' + value.roomId + '/connect', httpOptions);
  }

  addProduct(roomId: number, value: any) {
    console.log("add Product")
    return this.http.post<Room>('api/rooms/' + roomId + '/addProduct', {
      name: value.productName,
      amount: value.productAmount
    }, httpOptions);
  }

  addToProductMe(roomId: number, userId: string | null, productId: number | undefined) {
    console.log("add to product me")
    return this.http.post<Room>('api/rooms/' + roomId + '/addUserToProduct', {
      productId: productId,
      userId: userId
    }, httpOptions);
  }
}
