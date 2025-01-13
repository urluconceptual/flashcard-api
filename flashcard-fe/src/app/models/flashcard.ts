import {DifficultyLevel} from './difficulty-level';

export interface Flashcard {
  term: string;
  definition: string;
  difficultyLevel: DifficultyLevel;
}
