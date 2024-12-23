import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
  })
  export class RequestService {
    private apiUrl = 'http://localhost:8080/api/'

    private apiUrlRole = 'roles';
    private apiUrlDepartment = 'department';
  
    constructor(private http: HttpClient) {}
  
    getRoles(): Observable<string[]> {
      return this.http.get<string[]>(this.apiUrl + this.apiUrlRole);
    }

    getDepartment(): Observable<string[]> {
        return this.http.get<string[]>(this.apiUrl + this.apiUrlDepartment);
    }
  }