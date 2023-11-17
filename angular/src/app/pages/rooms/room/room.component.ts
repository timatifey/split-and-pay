import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiService} from "../../../service/api.service";
import {Room} from "../../../service/dto/room";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../../../service/user-storage/storage.service";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  roomId: number;
  room?: Room = new Room({id: 0, name: "roomName"});

  addProductForm: FormGroup;
  productName: FormControl = new FormControl(null, Validators.required);
  productAmount: FormControl = new FormControl(null, Validators.required);

  constructor(
    private api: ApiService,
    private storageService: StorageService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.roomId = Number(this.route.snapshot.paramMap.get('id'));
    console.log(this.roomId)
    this.api.getRoom(this.roomId)
      .subscribe({
        next: data => {
          this.room = data;
          console.log(data);
        },
        error: e => console.error(e)
      });
    console.log(this.room)

    this.addProductForm = new FormGroup({
      productName: this.productName,
      productAmount: this.productAmount
    });
  }

  ngOnInit(): void {

  }


  addProduct() {
    this.api.addProduct(this.roomId, this.addProductForm.value).subscribe({
      next: data => {
        console.log(data);
        this.room = data;
      }
    })

  }

  addToProductMe(id: number | undefined) {
    this.api.addToProductMe(this.roomId, this.storageService.getUserId(), id).subscribe({
      next: data => {
        console.log(data);
        this.room = data;
      }
    })
  }
}
