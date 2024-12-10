import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { TicketComponent } from './ticket/ticket.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
  { path: '', component: HomepageComponent }, // Default route
  { path: 'ticket', component: TicketComponent }, // Route for the Ticket page
  { path: '**', redirectTo: '' } // Redirect unknown routes to homepage
];

// Export the routes as a module so the app module can import it
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }