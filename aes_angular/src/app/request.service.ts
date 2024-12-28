import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RequestService {
  private apiUrl = 'http://localhost:8080/api/'

  private apiUrlRole = 'roles';
  private apiUrlService = 'service';
  private apiUrlStatus = 'status';
  private apiUrlRequest = 'request';
  private apiUrlRequestCreate = 'create';
  private apiUrlWorker = 'worker';

  constructor(private http: HttpClient) { }

  getRoles(): Observable<{ id: number, name: string }[]> {
    return this.http.get<{ id: number, name: string }[]>(this.apiUrl + this.apiUrlRole);
  }

  getServices(): Observable<{ id: number, name: string }[]> {
    return this.http.get<{ id: number, name: string }[]>(this.apiUrl + this.apiUrlService);
  }

  getStatuses(): Observable<{ id: number, name: string }[]> {
    return this.http.get<{ id: number, name: string }[]>(this.apiUrl + this.apiUrlStatus);
  }

  getRequests(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl + this.apiUrlRequest);
  }

  createRequest(request: { workerId: number, roleId: number, serviceId: number }): Observable<any> {
    return this.http.post<any>(this.apiUrl + this.apiUrlRequestCreate, request);
  }
}