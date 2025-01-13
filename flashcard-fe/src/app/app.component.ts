import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {Button} from 'primeng/button';
import {FlashcardCollectionComponent} from './components/flashcard-collection/flashcard-collection.component';
import {CollectionGalleryComponent} from "./components/collection-gallery/collection-gallery.component";
import {HeaderComponent} from "./components/header/header.component";
import {AuthenticationService} from "./services/authentication.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, Button, FlashcardCollectionComponent, CollectionGalleryComponent, HeaderComponent, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  constructor(protected authenticationService: AuthenticationService) {
  }
}
