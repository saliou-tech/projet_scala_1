import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfesseurService {
  // baseurl = 'http://localhost:8080';
  baseurl = 'http://localhost:9000';
    // baseurl =  'https://api-mouride-style-authentique.herokuapp.com'; 
 
   httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { 

  }
  public addProf(prof:any): Observable<any> {
    console.log(prof);
    return this.http.post(this.baseurl + '/api/professeur/add', prof);
  }
}
