import {Component, Input} from '@angular/core';
import { Card } from 'primeng/card';
import { NgIf } from '@angular/common';
import { Divider } from 'primeng/divider';
import { Flashcard } from '../../models/flashcard';

@Component({
  selector: 'app-flashcard',
  standalone: true,
  imports: [Card, NgIf, Divider],
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
