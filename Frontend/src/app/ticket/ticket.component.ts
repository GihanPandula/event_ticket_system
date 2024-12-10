import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ServiceService } from '../service.service';

// Ticket component
@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css'],
  standalone: true,
  imports: [FormsModule, HttpClientModule], 
})
export class TicketComponent implements OnInit {  // Ticket component class
  maxTickets: number;
  customerRate: number;
  ticketReleaseRate: number;
  availableTickets: number;
  totalTickets: number; 
  ticketCount: number;  

  // Constructor
  constructor(private route: ActivatedRoute, private service: ServiceService) {
    this.maxTickets = 0;
    this.customerRate = 0;
    this.ticketReleaseRate = 0;
    this.availableTickets = 0;
    this.totalTickets = 0; 
    this.ticketCount = 0; 
  }

  // Initialize component
  ngOnInit(): void {
    this.route.queryParams.subscribe((params: any) => {
      console.log(params); 
    });
    this.loadAvailableTickets();
  }

  // Load available tickets
  loadAvailableTickets(): void {
    this.service.getAvailableTickets().subscribe(
      (response) => {
        console.log('API Response:', response); // Debug response
        this.availableTickets = response; 
      },
      (error) => {
        console.error('Error loading tickets:', error); // Debug error
      }
    );
  }

  // Add tickets
  addTickets(): void {
    const ticketData = {
      totalTickets: this.totalTickets,
      maxTicketCapacity: this.maxTickets,
      ticketReleaseRate: this.ticketReleaseRate, 
      customerRetrievalRate: this.customerRate,
    };
  
    // Debug payload
    console.log('Payload sent to API:', ticketData);
  
    // Initialize tickets
    this.service.initializeTickets(ticketData).subscribe(
      (response) => {  
        console.log('Tickets initialized:', response); // Debug response
        this.loadAvailableTickets(); // Refresh available tickets after adding new tickets
      },
      (error) => {
        console.error('Error initializing tickets:', error); // Debug error
      }
    );
  }
  
  // Purchase tickets
  purchaseTickets(): void {
    this.service.getAvailableTickets().subscribe((available) => {
      if (this.ticketCount > available) {
        alert('Not enough tickets available!'); // Alert user if not enough tickets are available
      } else {
        this.service.purchaseTickets(this.ticketCount).subscribe( // Purchase tickets
          (response) => {
            console.log('Tickets purchased:', response);
            this.loadAvailableTickets(); // Refresh tickets
          },
          (error) => {
            console.error('Error purchasing tickets:', error);
          }
        );
      }
    });
  }  

  // Reset tickets
  resetTickets(): void {
    this.service.resetTickets().subscribe(
      (response) => {
        console.log('Tickets reset:', response);
        this.loadAvailableTickets(); // Reload available tickets after reset
      },
      (error) => {
        console.error('Error resetting tickets:', error);
      }
    );
  }

  // Reset form values
  resetForm(): void {
    this.maxTickets = 0;
    this.customerRate = 0;
    this.ticketReleaseRate = 0;
    this.totalTickets = 0;
    this.ticketCount = 0;
  }
}
