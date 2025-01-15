import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Button} from "primeng/button";
import {Dialog} from "primeng/dialog";
import {FloatLabel} from "primeng/floatlabel";
import {FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {IconField} from "primeng/iconfield";
import {InputIcon} from "primeng/inputicon";
import {InputText} from "primeng/inputtext";
import {NgForOf, NgIf} from "@angular/common";
import {Toast} from "primeng/toast";
import {Flashcard} from "../../models/flashcard";
import {FormUtil} from "../util/form.util";
import {Collection} from "../../models/collection";
import {MessageService} from "primeng/api";
import {FlashcardService} from "../../services/flashcard.service";
import {InputNumber} from "primeng/inputnumber";

@Component({
  selector: 'app-flashcards-dialog',
  standalone: true,
  imports: [
    Button,
    Dialog,
    FloatLabel,
    FormsModule,
    IconField,
    InputIcon,
    InputText,
    NgIf,
    ReactiveFormsModule,
    Toast,
    NgForOf,
    InputNumber
  ],
  templateUrl: './flashcards-dialog.component.html',
  styleUrl: './flashcards-dialog.component.scss'
})
export class FlashcardsDialogComponent implements OnInit {
  @Input() visible: boolean = false;
  @Input() flashcards?: Flashcard[];
  @Input() collectionId!: number;
  @Output() closed = new EventEmitter<void>();

  public form!: FormGroup;
  private controls!: any;

  ngOnInit() : void {
  this.controls = {
      term: new FormControl(undefined, [
        Validators.required
      ]),
      definition: new FormControl(undefined, [
        Validators.required
      ]),
      difficulty: new FormControl(undefined, [
        Validators.required
      ]),
      collectionId: new FormControl(this.collectionId)
    };

  this.form = new FormGroup({
      flashcards: new FormArray([
        new FormGroup(this.controls)
      ])
    });
  }

  public get flashcardControls(): FormArray {
    return this.form.controls["flashcards"] as FormArray;
  }

  constructor(private messageService: MessageService, private flashcardService: FlashcardService) {
  }

  onRemoveFlashcard(i: number): void {
    this.flashcardControls.removeAt(i);
  }

  onSave(): void {
    if (FormUtil.isFormInvalid(this.form)) {
      FormUtil.showErrorMessage(this.messageService);
      return;
    }

    if (this.flashcards) {
      this.onClose();
      return;
    } else {
      this.flashcardService.updateFlashcardsOfCollection(
        this.flashcardControls.value as Flashcard[]
      , this.messageService);
    }
    this.closed.emit();
  }

  onClose(): void {
    if (this.flashcards)
      this.form.patchValue({flashcards: this.flashcards});
    else
      this.form.reset();
    this.closed.emit();
  }

  addRow(): void {
    this.flashcardControls.push(new FormGroup(this.controls));
  }
}
