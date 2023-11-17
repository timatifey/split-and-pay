import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../service/api.service";
import {Room} from "../../service/dto/room";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {
  rooms: Room[] = [new Room({id: 1, name: "room1"}), new Room({id: 2, name: "room2"}), new Room({
    id: 3,
    name: "room3"
  })];
  createRoomForm: FormGroup;
  roomName: FormControl = new FormControl(null, Validators.required);


  incomeRoomForm: FormGroup;
  roomId: FormControl = new FormControl(null, Validators.required);

  constructor(
    private api: ApiService,
    private router: Router,
  ) {
    this.createRoomForm = new FormGroup({
      roomName: this.roomName
    });

    this.incomeRoomForm = new FormGroup({
      roomId: this.roomId
    });
  }

  ngOnInit(): void {
    this.api.getRooms()
      .subscribe({
        next: data => {
          this.rooms = data;
          console.log(data);
        },
        error: e => console.error(e)
      });
  }

  goToRoom(id?: number) {

    this.router.navigateByUrl('/rooms/' + id, {replaceUrl: true}).then();
  }

  createRoom() {
    console.log(this.createRoomForm.value);

    this.api.createRoom(this.createRoomForm.value).subscribe({
      next: data => {
        console.log(data);
        this.rooms.push(data);
      }
    })

    this.createRoomForm.reset()
  }

  incomeRoom() {
    console.log(this.incomeRoomForm.value);
    this.api.incomeRoom(this.incomeRoomForm.value).subscribe({
      next: data => {
        console.log(data);
        this.rooms.push(data);
      }
    })
    this.incomeRoomForm.reset()
  }
}
