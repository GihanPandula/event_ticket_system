import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css'],
  standalone: true,
  imports: [FormsModule, HttpClientModule], 
})

// export class TicketComponent implements OnInit {
//   maxTickets: number;
//   customerRate: number;
//   ticketReleaseRate: number;
//   availableTickets: number;
//   totalTickets: number;
//   ticketCount: number;
//   isStarted: boolean = false; // New state to track if the process is started

//   constructor(private route: ActivatedRoute, private service: ServiceService) {
//     this.maxTickets = 0;
//     this.customerRate = 0;
//     this.ticketReleaseRate = 0;
//     this.availableTickets = 0;
//     this.totalTickets = 0;
//     this.ticketCount = 0;
//   }

//   ngOnInit(): void {
//     this.loadAvailableTickets();
//   }

//   startFunction(): void {
//     this.isStarted = true;  // Set to true when start is clicked
//     // Enable form fields and allow API calls
//   }

//   stopFunction(): void {
//     this.isStarted = false;  // Set to false when stop is clicked
//     // Disable form fields and prevent further API calls
//   }

//   // Load available tickets
//   loadAvailableTickets(): void {
//     this.service.getAvailableTickets().subscribe(
//       (response) => {
//         this.availableTickets = response;
//       },
//       (error) => {
//         console.error('Error loading tickets:', error);
//       }
//     );
//   }

//   addTickets(): void {
//     if (!this.isStarted) return;  // Prevent action if stop is clicked

//     const ticketData = {
//       totalTickets: this.totalTickets,
//       maxTicketCapacity: this.maxTickets,
//       ticketReleaseRate: this.ticketReleaseRate,
//       customerRetrievalRate: this.customerRate,
//     };

//     this.service.initializeTickets(ticketData).subscribe(
//       (response) => {
//         this.loadAvailableTickets();
//       },
//       (error) => {
//         console.error('Error initializing tickets:', error);
//       }
//     );
//   }

//   purchaseTickets(): void {
//     if (!this.isStarted) return;  // Prevent action if stop is clicked

//     this.service.getAvailableTickets().subscribe((available) => {
//       if (this.ticketCount > available) {
//         alert('Not enough tickets available!');
//       } else {
//         this.service.purchaseTickets(this.ticketCount).subscribe(
//           (response) => {
//             this.loadAvailableTickets();
//           },
//           (error) => {
//             console.error('Error purchasing tickets:', error);
//           }
//         );
//       }
//     });
//   }

//   resetForm(): void {
//     this.maxTickets = 0;
//     this.customerRate = 0;
//     this.ticketReleaseRate = 0;
//     this.totalTickets = 0;
//     this.ticketCount = 0;
//   }
// }

export class TicketComponent implements OnInit {
  maxTickets: number;
  customerRate: number;
  ticketReleaseRate: number;
  availableTickets: number;
  totalTickets: number;
  ticketCount: number;
  isStarted: boolean = false; // New state to track if the process is started

  constructor(private route: ActivatedRoute, private service: ServiceService) {
    this.maxTickets = 0;
    this.customerRate = 0;
    this.ticketReleaseRate = 0;
    this.availableTickets = 0;
    this.totalTickets = 0;
    this.ticketCount = 0;
  }

  ngOnInit(): void {
    this.loadAvailableTickets();
  }

  startFunction(): void {
    this.isStarted = true;  // Set to true when start is clicked
    // Enable form fields and allow API calls
  }

  stopFunction(): void {
    this.isStarted = false;  // Set to false when stop is clicked
    // Disable form fields and prevent further API calls
  }

  // Load available tickets
  loadAvailableTickets(): void {
    this.service.getAvailableTickets().subscribe(
      (response) => {
        this.availableTickets = response;
      },
      (error) => {
        console.error('Error loading tickets:', error);
      }
    );
  }

  addTickets(): void {
    if (!this.isStarted) return; // Prevent action if stop is clicked
  
    // Validate inputs
    const specialCharRegex = /[^a-zA-Z0-9]/; // Matches any character that is not a letter or number
  
    if (
      this.totalTickets <= 0 ||
      this.maxTickets <= 0 ||
      this.ticketReleaseRate <= 0 ||
      this.customerRate <= 0
    ) {
      alert('All values must be greater than 0.');
      return;
    }
  
    if (
      specialCharRegex.test(this.totalTickets.toString()) ||
      specialCharRegex.test(this.maxTickets.toString()) ||
      specialCharRegex.test(this.ticketReleaseRate.toString()) ||
      specialCharRegex.test(this.customerRate.toString())
    ) {
      alert('Special characters are not allowed.');
      return;
    }
  
    if (this.maxTickets > this.totalTickets) {
      alert('Max ticket count cannot exceed the total ticket count.');
      return;
    }
  
    const ticketData = {
      totalTickets: this.totalTickets,
      maxTicketCapacity: this.maxTickets,
      ticketReleaseRate: this.ticketReleaseRate,
      customerRetrievalRate: this.customerRate,
    };
  
    this.service.initializeTickets(ticketData).subscribe(
      (response) => {
        this.loadAvailableTickets();
      },
      (error) => {
        console.error('Error initializing tickets:', error);
      }
    );
  }
  
  purchaseTickets(): void {
    if (!this.isStarted) return; // Prevent action if stop is clicked
  
    const specialCharRegex = /[^a-zA-Z0-9]/; // Matches any character that is not a letter or number
  
    if (this.ticketCount <= 0) {
      alert('Ticket count must be greater than 0.');
      return;
    }
  
    if (specialCharRegex.test(this.ticketCount.toString())) {
      alert('Special characters are not allowed.');
      return;
    }
  
    this.service.getAvailableTickets().subscribe((available) => {
      if (this.ticketCount > available) {
        alert('Not enough tickets available!');
      } else {
        this.service.purchaseTickets(this.ticketCount).subscribe(
          (response) => {
            this.loadAvailableTickets();
          },
          (error) => {
            console.error('Error purchasing tickets:', error);
          }
        );
      }
    });
  }  

  resetForm(): void {
    this.maxTickets = 0;
    this.customerRate = 0;
    this.ticketReleaseRate = 0;
    this.totalTickets = 0;
    this.ticketCount = 0;
  }
}
