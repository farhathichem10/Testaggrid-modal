import { Component } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { DemandeService } from '../services/demande.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  file!: File ; 
  x:any
  formAutorisation!:FormGroup
  dem:any=[]
  downloadUrl: SafeResourceUrl | undefined;
  constructor(private serv:DemandeService,private formBuilder : FormBuilder,private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.getall()
    this.formAutorisation = this.formBuilder.group({
       
       

      dateDemande : [(new Date()).toLocaleDateString().substring(0,10),Validators.required],
      heurS : ['',Validators.required],
      heurR:['',Validators.required],
      typDemande: ['A'],
      minR:['',Validators.required],
      minS:['',Validators.required],
      txtDem:[''],
      codAut:['',Validators.required],
      datDebut:['',Validators.required],
      reponseChef:[''],
      txtChef:[''],
      reponse:[''],
      

      matPers:['10081'],
      codSoc:['01']
    });
  }

  DemandeAutorisation() {


     const formData = new  FormData();
     const article = this.formAutorisation.value;
     formData.append('file',this.file);
     formData.append('demande',JSON.stringify(article));
  //    this.emailBody.message=this.formAutorisation.get('txtDem').value 
  //    this.emailBody.subject="Autorisation"
  //    this.emailBody.to=" mouadhzidi@gmail.com"
  //  this.Notification.date_notif=new Date().toLocaleDateString().substring(0,10)
  //  this.Notification.libelle_notif="Demande"
  //  this.Notification.nom=this.formAutorisation.get('txtDem').value 
  //  this.Notification.type_notif="Autorisations"
  //  this.Notification.mat_pers=this.tokenService.getUser().matpers
  //  this.Notification.id_sender=this.tokenService.getUser().matpers
  //  this.Notification.etat_notif="N"
  //  this.Notification.cod_soc=this.tokenService.getUser().cod_soc
  
     console.log(this.formAutorisation.value)
  
     if(this.file==null)
     {
      this.serv.CreateDemWithoutFile(this.formAutorisation.value).subscribe(
        (event: any) => {
          if (event) {
        console.log("yesssssssssssssssssssssssssssssssssssssssssssss");
        
  

  
          }
          //  this.toastr.error('Echec ajout', 'Problème de suppression.');
         
           
            
        }
    );
     }else{ this.serv.upload(formData).subscribe(
         (event: any) => {
           if (event) {
       console.log("yessssss");
       
   
  
         
             
            }}
     );
    }
  
   }
  
  onChange(event:any) {
    this.file = event.target.files[0];
}

createHyperLink(params:any): any {
  console.log(params.id_libre_demande)



  if (!params) { return; }
  const spanElement = document.createElement('span');
  if(params){
    console.log("entred here");
    
   spanElement.innerHTML = `<a href="${this.homeUrl}" > ${params} </a> `;
   spanElement.addEventListener("click", ($event) => {
     $event.preventDefault();
     // The below code is used to navigate from one page to another page in angular. you can change it          // according to your requirement.
     this.serv
       .download(params.id_libre_demande)
       .subscribe((blob) => saveAs(blob, params.fileName));
   });
 }else{
   spanElement.innerHTML = `<h6>fichier inexistant</h6> `;

 }

  return spanElement;
}
get homeUrl(): string {
  return 'home';
}
getall() {
  this.serv.all().subscribe(
    (data: any) => {
      this.dem = data;
    
      

      console.log(data);
    },
    (error) => {
      console.log(error);
    }
  );
}
downloadFile(params: any) {
  // Call the createHyperLink method to get the anchor element
  const anchorElement = this.createHyperLink(params);

  // Check if the anchorElement is not null and trigger its click event
  if (anchorElement) {
    anchorElement.click();
  }
}

}
