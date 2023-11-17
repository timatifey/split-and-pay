import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Login} from "../../service/dto/login";
import {Router} from "@angular/router";
import {LoginService} from "../../service/login.service";
import {StorageService} from "../../service/user-storage/storage.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  signInForm: FormGroup;
  loginDto: Login = new Login();
  username:FormControl = new FormControl(null, Validators.required);

  constructor(
    private loginService: LoginService,
    private storageService: StorageService,
    private router: Router
  ) {
    this.signInForm = new FormGroup({
      username: this.username,
    });
  }

  login() {
    this.loginDto = new Login(this.signInForm.value);
    console.log(this.loginDto)
    this.loginService.login(this.loginDto.username).subscribe({
      next: isValid => {
        if (isValid) {
          this.storageService.saveUserId(isValid.id);
          this.router.navigateByUrl('/rooms', {replaceUrl: true}).then();
        } else {
          console.log('Authentication failed.')
        }
      },
      error: error => {
        console.log('Authentication failed: ' + error)
      }
    })

  }

  ngOnInit(): void {
  }


}
