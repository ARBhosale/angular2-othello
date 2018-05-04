import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './loginPlayer/login.component';
import { CreateAccountComponent } from './services/createPlayerAccount/createAccount.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const appRoutes = [
    { path: 'login', component: LoginComponent },
    { path: 'create', component: CreateAccountComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path: '', pathMatch: 'full', redirectTo: '/login' },
    { path: '**', component: LoginComponent }

];


export const routing = RouterModule.forRoot(appRoutes);