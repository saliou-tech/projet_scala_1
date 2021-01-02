import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddProfComponent } from './Professeur/add-prof/add-prof.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'add-prof', component: AddProfComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
