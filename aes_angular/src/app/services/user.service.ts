import { Injectable } from '@angular/core';
import { ArtWorker } from '../models/tables';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private workerId: number | null = null;
  private userId: number | null = null;
  private roleId: number | null = null;

  private fullName: string | null = null;
  
  constructor() {}

  setWorkerId(id: number): void {
    this.workerId = id;
  }

  getWorkerId(): number | null {
    return this.workerId;
  }

  setUserId(id: number): void {
    this.userId = id;
  }

  getUserId(): number | null {
    return this.userId;
  }

  setRoleId(id: number): void {
    this.roleId = id;
  }

  getRoleId(): number | null {
    return this.roleId;
  }

  setFullNameId(name: string): void {
    this.fullName = name;
  }

  getFullNameId(): string | null {
    return this.fullName;
  }

  logout() {
    this.workerId = null;
    this.roleId = null;
    this.userId = null;
    this.fullName = null;
  }
}
