import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users'; // Backend endpoint

  constructor(private http: HttpClient) {}

  registerUser(userData: any): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/register`, userData);
  }

  loginUser(loginData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginData);
  }
}
