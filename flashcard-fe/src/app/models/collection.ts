import {Flashcard} from "./flashcard";

export type Collection = {
  collectionId: number,
  name: string,
  category: string,
  flashcardList: Flashcard[],
  userId?: string
  flashcardCount: number
}
