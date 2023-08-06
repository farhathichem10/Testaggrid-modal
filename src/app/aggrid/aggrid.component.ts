import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GridApi } from "ag-grid-community";
import { UserService } from '../services/user.service';
import { FormBuilder } from '@angular/forms';
import { ClientSideRowModelModule } from "@ag-grid-community/client-side-row-model";
import { Module } from '@ag-grid-community/core';

import { SweetAlert } from 'sweetalert/typings/core';


@Component({
  selector: 'app-aggrid',
  templateUrl: './aggrid.component.html',
  styleUrls: ['./aggrid.component.css']
})
export class AggridComponent implements OnInit  {

  api!: GridApi; 
  rowData: any[] = [];
  constructor(private serv:UserService,private formBuilder : FormBuilder,private modalService: NgbModal){}
  ngOnInit(): void {
    

    this.getalluser()
  }
  getalluser() {
    this.serv.getall().subscribe(
      (data: any) => {
        this.rowData = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  columnDefs = [
    {
      headerName: "email",
      field: "email",
      editable: true,
      resizable: true,
      sortable: true,
      filter: true,
      floatingFilter: true,
      width: 400,
    },
    {
      headerName: "username",
      field: "username",
      editable: true,
      resizable: true,
      sortable: true,
      filter: true,
      floatingFilter: true,
      width: 400,
    },
    {
      headerName: "password",
      field: "password",
      editable: true,
      resizable: true,
      sortable: true,
      filter: true,
      floatingFilter: true,
      width: 350,
    },
  ];
  defaultColDef = {
    sortable: true,
    filter: true,
  };

  modules: Module[] = [ClientSideRowModelModule];
}
