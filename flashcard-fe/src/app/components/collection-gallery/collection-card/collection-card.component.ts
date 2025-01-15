import {Component, Input} from '@angular/core';
import {Card} from "primeng/card";
import {Router, RouterLink} from "@angular/router";
import {Collection} from "../../../models/collection";
import {Button} from "primeng/button";
import {CollectionService} from "../../../services/collection.service";
import {MessageService} from "primeng/api";
import {CollectionDialog} from "../../collection-dialog/collection-dialog";
import {AuthenticationService} from "../../../services/authentication.service";
import {NgIf} from "@angular/common";
import {FlashcardsDialogComponent} from "../../flashcards-dialog/flashcards-dialog.component";

@Component({
  selector: 'app-collection-card',
  standalone: true,
  imports: [
    Card,
    RouterLink,
    Button,
    CollectionDialog,
    NgIf,
    FlashcardsDialogComponent
  ],
  templateUrl: './collection-card.component.html',
  styleUrl: './collection-card.component.scss'
})
export class CollectionCardComponent {
  @Input() collection!: Collection;
  public collectionDialogVisible: boolean = false;
  public flashcardDialogVisible: boolean = false;

  constructor(private router: Router, private collectionService: CollectionService, private messageService: MessageService, private authService: AuthenticationService) {
  }

  public onAddFlashcard(): void {
    console.log(this.collection);
    this.flashcardDialogVisible = true;
  }

  public onEdit() {
    this.collectionDialogVisible = true;
  }

  public onDelete(collection: Collection) {
    this.collectionService.deleteCollection(collection, this.messageService);
  }

  public onView(collection: Collection): void {
    this.router.navigate(['/collection', collection.collectionId]);
  }

  public onCollectionDialogClose(): void {
    this.collectionDialogVisible = false;
  }

  public collectionOwner(): boolean {
    return this.collection.userId === this.authService.userData?.userId;
  }

  public onFlashcardsDialogClosed(): void {
    this.flashcardDialogVisible = false;
  }
}
