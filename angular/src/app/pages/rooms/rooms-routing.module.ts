import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoomsComponent} from "./rooms.component";

const routes: Routes = [
    {path: '', component: RoomsComponent},
    {
        path: ':id',
        loadChildren: () => import('../rooms/room/room.module').then(m => m.RoomModule),
        canLoad: []
    },
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RoomsRoutingModule {
}
