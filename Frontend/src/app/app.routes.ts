import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

export class AppModule { }

export class RegisterComponent {
  user = {
    confirmPassword: ''
  };
}

export const routes: Routes = [
  { path: '', component: LoginComponent },
  {
    path: 'register',
    loadComponent: () => import('./register/register.component').then(m => m.RegisterComponent)
  }

];
