import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    // {path: "login", component: SignInComponent},

    {
        path: 'login',
        loadChildren: () => import('./pages/login/login.module').then(m => m.LoginModule),
    },
    {
        path: 'user',
        loadChildren: () => import('./pages/user/user.module').then(m => m.UserModule),
    },
    {
        path: 'rooms',
        loadChildren: () => import('./pages/rooms/rooms.module').then(m => m.RoomsModule),
    },
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }