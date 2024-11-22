// import { Component } from '@angular/core';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-login',
//   standalone: true,
//   imports: [],
//   templateUrl: './login.component.html',
//   styleUrl: './login.component.css'
// })
// export class LoginComponent {
//   constructor(private router: Router) {}

//   navigateToRegister() {
//     this.router.navigate(['/register']);
//   }
// }


import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule

@Component({
  selector: 'app-login',
  standalone: true, // This indicates it's a standalone component
  imports: [FormsModule], // Add FormsModule here
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData = {
    email: '',
    password: ''
  };

  onLogin() {
    console.log('User Login Data:', this.loginData);
  }

  onSubmit() {
    console.log('User Login Data:', this.loginData);
  }
}


