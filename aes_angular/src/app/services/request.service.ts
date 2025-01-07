import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ArtAccess, ArtRequest, ArtRole, ArtService, ArtStatus, ArtWorker } from '../models/tables';

@Injectable({
  providedIn: 'root',
})
export class RequestService {
  private apiUrl = 'http://localhost:8080/api/'

  private apiUrlRole = 'roles';
  private apiUrlService = 'service';
  private apiUrlStatus = 'status';
  private apiUrlPersonalRequest = 'request';
  private apiUrlAllRequest = 'requests';
  private apiUrlRequestCreate = 'create';
  private apiUrlWorker = 'worker';
  private apiUrlAccess = 'access';
  private apiUrlUpdateRequest = 'updateRequest';

  constructor(private http: HttpClient) { }

  getRoles(): Observable<ArtRole[]> {
    return this.http.get<ArtRole[]>(this.apiUrl + this.apiUrlRole);
  }

  getServices(): Observable<ArtService[]> {
    return this.http.get<ArtService[]>(this.apiUrl + this.apiUrlService);
  }

  getStatuses(): Observable<ArtStatus[]> {
    return this.http.get<ArtStatus[]>(this.apiUrl + this.apiUrlStatus);
  }

  getPersonalRequests(workerId: number): Observable<ArtRequest[]> {
    const url = `${this.apiUrl}${this.apiUrlPersonalRequest}/${workerId}`;
    return this.http.get<ArtRequest[]>(url);
  }

  getAllRequests(): Observable<ArtRequest[]> {
    return this.http.get<ArtRequest[]>(this.apiUrl + this.apiUrlAllRequest);
  }

  createRequest(request: { workerId: number, roleId: number, serviceId: number }): Observable<any> {
    return this.http.post<any>(this.apiUrl + this.apiUrlRequestCreate, request);
  }

  getWorker(workerId: number): Observable<ArtWorker> {
    const url = `${this.apiUrl}${this.apiUrlWorker}/${workerId}`;
    return this.http.get<ArtWorker>(url);
  }

  getAccess(userId: number): Observable<ArtAccess[]> {
    const url = `${this.apiUrl}${this.apiUrlAccess}/${userId}`;
    return this.http.get<ArtAccess[]>(url);
  }

  updateRequestStatus(requestId: number, statusId: number, roleId: number): Observable<any> {
    const url = `${this.apiUrl}${this.apiUrlUpdateRequest}/${requestId}`;
    return this.http.patch(url, { statusId, roleId});
  }
}