// import { Component } from '@angular/core';
// import { FormsModule } from '@angular/forms';

// @Component({
//   selector: 'app-register',
//   templateUrl: './register.component.html',
//   styleUrls: ['./register.component.css'],
//   standalone: true,
//   imports: [FormsModule],
// })

// export class RegisterComponent {
//   user = {
//     firstName: '',
//     lastName: '',
//     contactNumber: '',
//     nicType: 'NIC',
//     nic: '',
//     country: 'Sri Lanka',
//     email: '',
//     verificationMethod: 'Email',
//     password: '',
//     confirmPassword: ''
//   };

//   onSubmit() {
//     if (this.user.password !== this.user.confirmPassword) {
//       alert('Passwords do not match');
//       return;
//     }

//     console.log('User registration details:', this.user);
//   }
// }


import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule

@Component({
  selector: 'app-register',
  standalone: true, // Indicating this is a standalone component
  imports: [FormsModule], // Include FormsModule here
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = {
    firstName: '',
    lastName: '',
    contactNumber: '',
    email: '',
    password: '',
    confirmPassword: ''
  };

  onSubmit() {
    console.log('User Registered:', this.user);
  }
}

