import {Injectable} from "@angular/core";
import {FlashcardCollection} from "../models/flashcard-collection";

@Injectable({providedIn: "root"})
export class FlashcardService {
  private collections = new Map<string, FlashcardCollection>([
    ['124256', {
      id: '124256',
      name: "Biology",
      flashcardList: [{
        term: 'Mitochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Bytochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Lightocondrya',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      },]
    },],
    ['124257', {
      id: '124257',
      name: "French",
      flashcardList: [{
        term: 'Mitochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Bytochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Lightocondrya',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      },
      ]
    },],
    ['124258', {
      id: '124258',
      name: "Maths",
      flashcardList: [{
        term: 'Mitochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Bytochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Lightocondrya',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      },
      ]
    },],
    ['124259', {
      id: '124259',
      name: "Astrology",
      flashcardList: [{
        term: 'Mitochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Bytochondria',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      }, {
        term: 'Lightocondrya',
        definition: 'The powerhouse of the cell.',
        difficultyLevel: 'Easy',
      },
      ]
    }],
  ]);

  public getCollections(): FlashcardCollection[] {
    return Array.from(this.collections.values());
  }

  public getCollectionById(id: string): FlashcardCollection | undefined {
    return this.collections.get(id);
  }
}
