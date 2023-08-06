import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const AUTH_APII = 'http://localhost:8080/demande/downloadFile/';
@Injectable({
  providedIn: 'root'
})
export class DemandeService {

  constructor(private http: HttpClient
    ) { }
    CreateDemWithoutFile(user:any){
      return this.http.post('http://localhost:8080/demande/addDemandeWithoutFile',user)
  
    }
    all(){
      return this.http.get('http://localhost:8080/demande/all')
  
    }
    upload(user:FormData){
      return this.http.post('http://localhost:8080/demande/createDemande',user)
  
    }
    download(file: number | undefined): Observable<Blob> {
      return this.http.get(AUTH_APII+file, {
        responseType: 'blob'
      });
    }
    
  }
  