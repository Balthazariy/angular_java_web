import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { RequestService } from '../request.service';

@Component({
  selector: 'app-create-request',
  imports: [
    FormsModule,
    CommonModule,
    HttpClientModule
  ],
  templateUrl: './create-request.component.html',
  styleUrl: './create-request.component.css',
  providers: [RequestService]
})
export class CreateRequestComponent {
  availableRoles: string[] = [];
  availableDeparments: string[] = [];

  request = {
    role: '',
    department: ''
  }

  constructor(private requestService: RequestService, private router: Router) { }


  ngOnInit() {
    this.loadRoles();
    this.loadDepartments();
  }

  loadRoles() {
    this.requestService.getRoles().subscribe({
      next: (data) => {
        this.availableRoles = data;
        console.log('Roles loaded:', data);
      },
      error: (err) => {
        console.error('Error loading roles:', err);
      }
    });
  }

  loadDepartments() {
    this.requestService.getDepartment().subscribe({
      next: (data) => {
        this.availableDeparments = data;
        console.log('Departments loaded:', data);
      },
      error: (err) => {
        console.error('Error loading departments:', err);
      }
    });
  }

  onSubmit() {
    console.log('Request submitted:', this.request);

  }
}
