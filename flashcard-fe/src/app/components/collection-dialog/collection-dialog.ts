import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatDialogActions, MatDialogContent, MatDialogTitle} from "@angular/material/dialog";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {Button, ButtonDirective, ButtonLabel} from "primeng/button";
import {FloatLabel} from "primeng/floatlabel";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputText} from "primeng/inputtext";
import {NgIf} from "@angular/common";
import {Password} from "primeng/password";
import {Ripple} from "primeng/ripple";
import {Dialog} from "primeng/dialog";
import {IconField} from "primeng/iconfield";
import {InputIcon} from "primeng/inputicon";
import {FormUtil} from "../util/form.util";
import {Collection} from "../../models/collection";
import {Toast} from "primeng/toast";
import {MessageService} from "primeng/api";
import {CollectionService} from "../../services/collection.service";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-collection-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    MatFormField,
    MatDialogTitle,
    MatInput,
    MatDialogActions,
    MatButton,
    MatLabel,
    Button,
    ButtonDirective,
    ButtonLabel,
    FloatLabel,
    FormsModule,
    InputText,
    NgIf,
    Password,
    ReactiveFormsModule,
    Ripple,
    Dialog,
    IconField,
    InputIcon,
    Toast
  ],
  templateUrl: './collection-dialog.html',
  styleUrl: './collection-dialog.scss'
})
export class CollectionDialog implements OnInit {
  @Input() visible: boolean = false;
  @Input() collection?: Collection;
  @Output() closed = new EventEmitter<void>();

  public form = new FormGroup({
    name: new FormControl('', [
      Validators.required
    ]),
    category: new FormControl('', [
      Validators.required
    ]),
  });

  constructor(private collectionService: CollectionService,
              private messageService: MessageService,
              private authService: AuthenticationService) {
  }

  ngOnInit(): void {
    if (this.collection) {
      this.form.patchValue(this.collection);
    }
  }

  public onClose(): void {
    if (this.collection)
      this.form.patchValue(this.collection);
    else
      this.form.reset();
    this.closed.emit();
  }

  public onSave(): void {
    if (FormUtil.isFormInvalid(this.form)) {
      FormUtil.showErrorMessage(this.messageService);
      return;
    }

    if (this.collection) {
      this.collectionService.updateCollection({
        ...this.form.value,
        collectionId: this.collection.collectionId
      } as Collection, this.messageService);
      this.onClose();
      return;
    } else {
      const userId = this.authService.userData?.userId;
      this.collectionService.createCollection({...this.form.value, userId} as Collection, this.messageService);
    }

    this.onClose();
  }

  public isControlInvalid(controlName: string, validationName: string): boolean {
    return FormUtil.isControlInvalid(this.form, controlName, validationName);
  }
}
