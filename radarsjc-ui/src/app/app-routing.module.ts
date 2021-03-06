import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RadarWeekComponent } from './components/radar/radar-week/radar-week.component';
import { RadarDayComponent } from './components/radar/radar-day/radar-day.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'week',
    component: RadarWeekComponent
  },
  {
    path: 'day',
    component: RadarDayComponent
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
