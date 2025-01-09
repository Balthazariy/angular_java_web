import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { RequestService } from '../../services/request.service';
import { UserService } from '../../services/user.service';
import { Request } from '../../models/request.model';
import { ArtAccess, ArtRole, ArtService, ArtStatus } from '../../models/tables';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-create-request',
  imports: [
    FormsModule,
    CommonModule,
    HttpClientModule
  ],
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
  providers: [RequestService]
})

export class MainComponent {
  availableRoles: ArtRole[] = [];
  availableServices: ArtService[] = [];
  availableStatuses: ArtStatus[] = [];
  allAccesses: ArtAccess[] = [];

  filteredRequests: any[] = [];
  personalRequests: any[] = [];
  allRequests: any[] = []

  requestData = {
    workerId: -1,
    roleId: -1,
    serviceId: -1,
    fullname: ''
  }

  ownerAccesses: any[] = []
  adminAccesses: any[] = []

  selection = {
    role: '',
    service: ''
  }

  isOwner: boolean = false;
  isAdmin: boolean = false;
  isPersonal: boolean = true;
  hideActionButtons: boolean = false;
  isError: boolean = false;
  errorMessage: string = '';

  constructor(
    private requestService: RequestService,
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.loadPage();
  }

  async loadPage() {
    try {
      await Promise.all([
        this.loadRoles(),
        this.loadStatuses(),
        this.loadServices(),
        this.loadWorker(),
      ]);

      await Promise.all([
        this.loadAllRequests(),
        this.loadPersonalRequests(),
        this.loadAccesses(),
      ]);

      await Promise.all([
        this.showPersonalRequests(),
      ]);
    } catch (err) {
      console.error('Помилка завантаження сторінки:', err);
    }
  }

  isPersonalEnabled(): boolean {
    return this.isPersonal;
  }

  isAdminEnabled(): boolean {
    return this.isAdmin;
  }

  isOwnerEnabled(): boolean {
    return this.isOwner;
  }

  showPersonalRequests() {
    this.filteredRequests = this.personalRequests.filter(request => request.roleName === 'Працівник');
    this.filterRequests(this.filteredRequests);
    this.hideActionButtons = true;
  }

  showAdminRequests() {
    this.filteredRequests = this.allRequests.filter(request => {
      const match = this.adminAccesses.some(adminAccess =>
        adminAccess.service === request.serviceName && request.statusId === 2
      );
      return match;
    });
    this.filterRequests(this.filteredRequests);
    this.hideActionButtons = false;
    console.log('Відфільтровано запити адміністратора:', this.filteredRequests);
  }

  showOwnerRequests() {
    this.filteredRequests = this.allRequests.filter(request => {
      const match = this.ownerAccesses.some(ownerAccess =>
        ownerAccess.service === request.serviceName && request.statusId === 1
      );
      return match;
    });

    this.filterRequests(this.filteredRequests);
    this.hideActionButtons = false;
    console.log('Відфільтровано запити власника:', this.filteredRequests);
  }

  setUserAccess() {
    this.allAccesses.forEach((element) => {
      if (element.roleName === 'Власник') {
        this.isOwner = true;
        this.ownerAccesses.push({
          service: element.serviceName,
          role: element.roleName
        });
      } else if (element.roleName === 'Адміністратор') {
        this.isAdmin = true;
        this.adminAccesses.push({
          service: element.serviceName,
          role: element.roleName
        });
      }
    });
  }

  async loadAccesses() {
    this.requestService.getAccess(this.userService.getUserId()!).subscribe({
      next: (data: any[]) => {
        this.allAccesses = data.map(item => ({
          id: item.id,
          userId: item.userId,
          serviceId: item.serviceId,
          roleId: item.roleId,
          description: item.description,
          roleName: this.getRoleNameById(item.roleId),
          serviceName: this.getServiceNameById(item.serviceId)
        }));
        this.setUserAccess();
        console.log('Доступи завантажено:', this.allAccesses);
      },
      error: (err) => {
        console.error('Помилка завантаження доступів:', err);
      }
    });
  }

  async loadRoles() {
    try {
      const data = await this.requestService.getRoles().toPromise();
      this.availableRoles = data!;
      console.log('Ролі завантажено:', data);
    } catch (err) {
      console.error('Помилка завантаження ролей:', err);
    }
  }

  async loadServices() {
    this.requestService.getServices().subscribe({
      next: (data) => {
        this.availableServices = data;
        console.log('Сервіси завантажено:', data);
      },
      error: (err) => {
        console.error('Помилка завантаження сервісів:', err);
      }
    });
  }

  async loadWorker() {
    this.requestService.getWorker(this.userService.getWorkerId()!).subscribe({
      next: (data) => {
        this.userService.setFullNameId(data.fullname);
        console.log('Робітник завантажено:', data);
      },
      error: (err) => {
        console.error('Помилка завантаження робітника:', err);
      }
    });
  }

  filterRequests(requests: any) {
    this.filteredRequests = [...requests];
    for (let index = 0; index < this.filteredRequests.length; index++) {
      this.filteredRequests[index].filteredId = index + 1
    }
  }

  async loadAllRequests() {
    this.requestService.getAllRequests().subscribe({
      next: (data: any[]) => {
        const workerIdToExclude = this.userService.getWorkerId();
        this.allRequests = data
          .filter(item => item.workerId !== workerIdToExclude)
          .map(item => ({
            id: item.id,
            workerId: item.workerId,
            serviceId: item.serviceId,
            roleId: item.roleId,
            statusId: item.statusId,
            requestDate: item.requestDate,
            roleName: this.getRoleNameById(item.roleId),
            serviceName: this.getServiceNameById(item.serviceId),
            statusName: this.getStatusNameById(item.statusId),
            fullName: item.fullname,
          }));
        console.log('Усі запити завантажено:', this.allRequests);
      },
      error: (err) => {
        console.error('Помилка завантаженна усіх запитів:', err);
      }
    });
  }


  async loadPersonalRequests() {
    this.requestService.getPersonalRequests(this.userService.getWorkerId()!).subscribe({
      next: (data: any[]) => {
        this.personalRequests = data.map(item => ({
          id: item.id,
          workerId: item.workerId,
          serviceId: item.serviceId,
          roleId: item.roleId,
          statusId: item.statusId,
          requestDate: item.requestDate,
          roleName: this.getRoleNameById(item.roleId),
          serviceName: this.getServiceNameById(item.serviceId),
          statusName: this.getStatusNameById(item.statusId),
          fullName: item.fullname,
        }));
        this.showPersonalRequests();
        console.log('Персональні запити успішно завантажено:', this.personalRequests);
      },
      error: (err) => {
        console.error('Помилка завантаження персональних запитів:', err);
      }
    });
  }

  async loadStatuses() {
    this.requestService.getStatuses().subscribe({
      next: (data) => {
        this.availableStatuses = data;
        console.log('Статуси успішно завантажено:', data);
      },
      error: (err) => {
        console.error('Помилка завантаження статусів:', err);
      }
    });
  }

  onSubmit() {
    this.requestData.workerId = this.userService.getWorkerId()!;
    this.requestData.roleId = this.availableRoles.find(role => role.name === this.selection.role)!.id;
    this.requestData.serviceId = this.availableServices.find(service => service.name === this.selection.service)!.id;
    this.requestData.fullname = this.userService.getFullNameId()!;
    this.isError = false;
    if (this.allAccesses.find(x => x.roleId == this.requestData.roleId) && this.allAccesses.find(x => x.serviceId == this.requestData.serviceId)) {
      console.log('Запит з такою самою роллю та сервісом вже існує!');
      this.errorMessage = "У вас вже є доступ"
      this.isError = true;
      return;
    }

    if (this.personalRequests.find(x => x.roleId == this.requestData.roleId) && this.personalRequests.find(x => x.serviceId == this.requestData.serviceId)) {
      console.log('Запит з такою самою роллю та сервісом вже існує!');
      this.errorMessage = "Запит з такою роллю та сервісом вже існує"
      this.isError = true;
      return;
    }
    this.requestService.createRequest(this.requestData).subscribe({
      next: (response) => {
        console.log('Запит успішно сформовано:', response);
        this.loadPersonalRequests();
      },
      error: (error) => {
        console.error('Помилка у формуванні запиту:', error);
      }
    });
  }

  updateRequestStatus(request: any, newStatus: number) {
    request.statusId = newStatus;
    this.requestService.updateRequestStatus(request.id, newStatus, request.roleId).subscribe({
      next: () => {
        console.log('Статус оновлено:', request);
        this.filterRequests(this.allRequests);
      },
      error: (err) => {
        console.error('Помилка при оновленні статусу:', err);
      }
    });
  }

  approveRequest(request: any) {
    if (this.isOwner && request.statusId === 1) {
      this.updateRequestStatus(request, 2);
    } else if (this.isAdmin && request.statusId === 2) {
      this.updateRequestStatus(request, 4);
    }
  }

  rejectRequest(request: any) {
    if (this.isOwner && request.statusId === 1) {
      this.updateRequestStatus(request, 3);
    } else if (this.isAdmin && request.statusId === 2) {
      this.updateRequestStatus(request, 5);
    }
  }

  logout() {
    this.availableRoles = []
    this.availableServices = []
    this.availableStatuses = []
    this.allAccesses = []
    this.filteredRequests = []
    this.personalRequests = []
    this.requestData = {
      workerId: -1,
      roleId: -1,
      serviceId: -1,
      fullname: ''
    }
    this.ownerAccesses = []
    this.adminAccesses = []
    this.selection = {
      role: '',
      service: ''
    }
    this.isOwner = false
    this.isAdmin = false
    this.isPersonal = true

    this.userService.logout();
    this.router.navigate(['/login']);
  }

  getRoleNameById(roleId: number): string {
    const role = this.availableRoles.find(role => role.id == roleId);
    return role!.name;
  }

  getServiceNameById(serviceId: number): string {
    const service = this.availableServices.find(service => service.id == serviceId);
    return service!.name;
  }

  getStatusNameById(statusId: number): string {
    const status = this.availableStatuses.find(status => status.id == statusId);
    return status!.name;
  }
}