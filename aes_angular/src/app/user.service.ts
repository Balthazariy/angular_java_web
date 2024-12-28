import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private workerId: number | null = null;

  constructor() {}

  setWorkerId(id: number): void {
    this.workerId = id;
  }

  getWorkerId(): number | null {
    return this.workerId;
  }
}
