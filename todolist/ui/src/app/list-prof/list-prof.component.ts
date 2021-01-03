import { Component, OnInit, ViewChild} from '@angular/core';
import { ProfesseurService } from '../professeur.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-list-prof',
  templateUrl: './list-prof.component.html',
  styleUrls: ['./list-prof.component.scss']
})
export class ListProfComponent implements OnInit {
  listprof=[];
  dataSource:any;


displayedColumns: string[] = ['numero', 'nom' ,'prenom','grade','Actions'];

@ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
constructor(private profservice:ProfesseurService) {
  this.paginator=this.dataSource;
 }

  ngOnInit(): void {
    this.listProfesseurs()
     this.dataSource.paginator = this.paginator
    

  }


  listProfesseurs(){
    this.profservice.readProfs().subscribe(
      data => {
        this.listprof = data;

        console.log( "listarticles" ,this.listprof);
        
        this.dataSource = new MatTableDataSource(this.listprof);
        console.log( "les donnnes",this.dataSource)
        this.dataSource.paginator = this.paginator
  
    },
    error => {
      console.log('error', error);
    })

  }

}
