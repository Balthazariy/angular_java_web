import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../login.service';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    CommonModule,
    HttpClientModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  providers: [LoginService]
})
export class LoginComponent {
  loginData = {
    username: '',
    password: ''
  }

  isError: boolean = false;
  errorMessage: string = '';

  constructor(
    private loginService: LoginService, 
    private router: Router,
    private userService: UserService
  ) { }

  onSubmit() {
    this.loginService.login(this.loginData.username, this.loginData.password).subscribe(
      (response) => {
        this.userService.setWorkerId(response.workerId)
        this.router.navigate(['/create_request'])
        this.isError = false;
      },
      (error) => {
        this.isError = true;
        this.errorMessage = error.error;
      }
    );
  }
}
