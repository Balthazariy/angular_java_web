import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CreateRequestComponent } from './create-request/create-request.component';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet, 
    LoginComponent,
    CreateRequestComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'aes';
}