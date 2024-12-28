import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { RequestService } from '../request.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-create-request',
  imports: [
    FormsModule,
    CommonModule,
    HttpClientModule
  ],
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.css'],
  providers: [RequestService]
})

export class CreateRequestComponent {
  availableRoles: { id: number; name: string; }[] = [];
  availableServices: { id: number; name: string; }[] = [];
  availableStatuses: { id: number; name: string; }[] = [];

  filteredRequests: any[] = [];
  allRequests: any[] = [];

  requestData = {
    workerId: -1,
    roleId: -1,
    serviceId: -1
  }

  selection = {
    role: '',
    service: ''
  }

  isOwner = false;
  isAdmin = false;

  filterStatus = '';

  constructor(
    private requestService: RequestService, 
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.loadRoles();
    this.loadServices();
    this.loadRequests();
    this.loadStatuses();
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

  loadServices() {
    this.requestService.getServices().subscribe({
      next: (data) => {
        this.availableServices = data;
        console.log('Services loaded:', data);
      },
      error: (err) => {
        console.error('Error loading services:', err);
      }
    });
  }

  loadRequests() {
    this.requestService.getRequests().subscribe({
      next: (data) => {
        this.allRequests = data;
        console.log('Requests loaded:', data);
        this.filterRequests();
      },
      error: (err) => {
        console.error('Error loading requests:', err);
      }
    });
  }

  loadStatuses() {
    this.requestService.getStatuses().subscribe({
      next: (data) => {
        this.availableStatuses = data;
        console.log('Statuses loaded:', data);
      },
      error: (err) => {
        console.error('Error loading statuses:', err);
      }
    });
  }

  filterRequests() {
    if (this.filterStatus) {
      this.filteredRequests = this.allRequests.filter(request => request.status === this.filterStatus);
    } else {
      this.filteredRequests = [...this.allRequests];
    }
  }

  onSubmit() {
    this.requestData.workerId = this.userService.getWorkerId()!;
    this.requestData.roleId = this.availableRoles.find(role => role.name === this.selection.role)!.id;
    this.requestData.serviceId = this.availableServices.find(service => service.name === this.selection.service)!.id;
    console.log(this.requestData);
    this.requestService.createRequest(this.requestData).subscribe({
      next: (response) => {
        console.log('Request successfully submitted:', response);
      },
      error: (error) => {
        console.error('Error submitting request:', error);
      }
    });
  }

  approveRequest(request: any) {
    console.log('Request approved:', request);
    request.status = '2';
    this.filterRequests();
  }

  rejectRequest(request: any) {
    console.log('Request rejected:', request);
    request.status = '3';
    this.filterRequests();
  }
}