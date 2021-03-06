import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RadarDia } from '../../model/RadarDia';

const API: string = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class RadarService {

  constructor(
    private http: HttpClient
  ) { }

  getWeek() {
    return this.http.get<RadarDia[]>(`${API}/lista`);
  }

  getDay(day: string) {
    return this.http.get<RadarDia>(`${API}/${day}`);
  }
}
