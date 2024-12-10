import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

// Service for ticket endpoints
@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private baseUrl = 'http://localhost:8080/api/tickets'; // Base URL for ticket endpoints

  // Inject HttpClient
  constructor(private http: HttpClient) {}

  // Get available tickets
  getAvailableTickets(): Observable<number> {
    return this.http.get<any>(`${this.baseUrl}/current`).pipe(
      map(response => response.currentTickets) // Match API response field
    );
  }
  
  // reset tickets
  resetTickets(): Observable<any> {
    return this.http.post(`${this.baseUrl}/reset`, {});
  }  

  // purchase tickets
  purchaseTickets(ticketCount: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/purchase?count=${ticketCount}`, {});
  }

  // Initialize tickets with new data
  initializeTickets(ticketData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/initialize`, ticketData);
  }  
}
