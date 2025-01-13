import {Routes} from '@angular/router';
import {CollectionGalleryComponent} from "./components/collection-gallery/collection-gallery.component";
import {FlashcardCollectionComponent} from "./components/flashcard-collection/flashcard-collection.component";
import {AuthenticationComponent} from "./components/authentication/authentication.component";

export const routes: Routes = [
  {path: 'collections', component: CollectionGalleryComponent},
  {path: 'auth', component: AuthenticationComponent},
  {path: 'collection/:collectionId', component: FlashcardCollectionComponent},
  {path: '', redirectTo: 'auth', pathMatch: 'full'}
];
