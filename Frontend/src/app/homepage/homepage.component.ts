import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

// HomepageComponent component
@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})

// HomepageComponent class
export class HomepageComponent {
  tickets = [
    {
      title: 'Metallica Live in Colombo 2024',
      date: new Date(2024, 11, 13, 19, 0), // Dec 13, 2024, 7:00 PM
      venue: 'Galle Face',
      price: 2000,
      image: 'https://via.placeholder.com/300x200?text=Metalica+Concert'
    },
    {
      title: 'Maroon 5 Live in Colombo 2024',
      date: new Date(2024, 11, 13, 19, 0), // Dec 13, 2024, 7:00 PM
      venue: 'Galle Face',
      price: 2000,
      image: 'https://via.placeholder.com/300x200?text=Maroon 5+Concert'
    },
    {
      title: 'Coldplay Live in Colombo 2024',
      date: new Date(2024, 11, 13, 20, 0), // Dec 13, 2024, 8:00 PM
      venue: 'Galle Face',
      price: 6000,
      image: 'https://via.placeholder.com/300x200?text=Coldplay+Concert'
    },
  ];
}
