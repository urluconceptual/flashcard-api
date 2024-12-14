import { Routes } from '@angular/router';
import { CollectionGalleryComponent } from "./components/collection-gallery/collection-gallery.component";
import { FlashcardCollectionComponent } from "./components/flashcard-collection/flashcard-collection.component";

export const routes: Routes = [
  {path: 'collections', component: CollectionGalleryComponent},
  {path: 'collection/:collectionId', component: FlashcardCollectionComponent},
  {path: '', redirectTo: 'collections', pathMatch: 'full'}
];
