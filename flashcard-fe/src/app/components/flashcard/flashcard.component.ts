import {Component, Input} from '@angular/core';
import {NgIf} from '@angular/common';
import {Divider} from 'primeng/divider';
import {Flashcard} from '../../models/flashcard';

@Component({
  selector: 'app-flashcard',
  standalone: true,
  imports: [NgIf, Divider],
  templateUrl: './flashcard.component.html',
  styleUrl: './flashcard.component.scss',
})
export class FlashcardComponent {
  @Input() public flashcard!: Flashcard;
  public expanded = false;

  public onClick(event: MouseEvent) {
    event.preventDefault();
    this.expanded = !this.expanded;
  }
}
