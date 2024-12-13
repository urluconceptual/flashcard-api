import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Button } from 'primeng/button';
import { FlashcardCollectionComponent } from './components/flashcard-collection/flashcard-collection.component';
import {CollectionGalleryComponent} from "./components/collection-gallery/collection-gallery.component";
import {TopMenuBarComponent} from "./components/top-menu-bar/top-menu-bar.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, Button, FlashcardCollectionComponent, CollectionGalleryComponent, TopMenuBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'flashcard-fe';
}
