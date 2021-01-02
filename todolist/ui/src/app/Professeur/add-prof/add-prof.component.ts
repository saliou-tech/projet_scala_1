import { Component, OnInit } from '@angular/core';
import { ProfesseurService } from 'src/app/professeur.service';

@Component({
  selector: 'app-add-prof',
  templateUrl: './add-prof.component.html',
  styleUrls: ['./add-prof.component.scss']
})
export class AddProfComponent implements OnInit {
newprof={
  nomProfesseur:'',
  prenomProfesseur:'',
  grade:''
}
  constructor(private profservice:ProfesseurService) { }

  ngOnInit(): void {
    this.newprof={
      nomProfesseur:'',
  prenomProfesseur:'',
  grade:''

    }
  }
  addProfesseur(){
    console.log(this.newprof)
    this.profservice.addProf(this.newprof).subscribe(
      data => {
        // this.token = data;
        console.log('item delete successfuly')
        console.log('response', data);
        //  this.getArticles();
      
    
    
      } ,
      error => {
        console.log('error', error);
        // this.iserrors = true;
      });
  }
  cancelAddProfesseur(){
    this.newprof={
      nomProfesseur:'',
      prenomProfesseur:'',
      grade:''
    }
  }

}
