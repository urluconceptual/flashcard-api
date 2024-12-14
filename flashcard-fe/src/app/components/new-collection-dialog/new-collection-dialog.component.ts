import { Component, Inject } from '@angular/core';
import { CreateCollectionService } from "../../services/create-collection.service";
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from "@angular/material/dialog";

@Component({
  selector: 'app-new-collection-dialog',
  standalone: true,
  imports: [],
  templateUrl: './new-collection-dialog.component.html',
  styleUrl: './new-collection-dialog.component.scss'
})
export class NewCollectionDialogComponent {
  constructor(private createCollectionService: CreateCollectionService,
              private dialogRef: MatDialogRef<NewCollectionDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {}

  public onSubmit() {
    this.createCollectionService.submitData();
  }

}
