import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './loginPlayer/login.component';
import { CreateAccountComponent } from './services/createPlayerAccount/createAccount.component';

const appRoutes =[
    {path: 'login', component: LoginComponent},
    {path: 'create', component: CreateAccountComponent},
    {path: '', pathMatch: 'full', redirectTo: 'add'}

];


export const routing = RouterModule.forRoot(appRoutes);