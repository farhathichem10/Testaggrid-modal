import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddComponent } from './add/add.component';
import { AggridComponent } from './aggrid/aggrid.component';

const routes: Routes = [
  {path: 'add', component: AddComponent},
  {path: 'ag', component: AggridComponent},



];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
