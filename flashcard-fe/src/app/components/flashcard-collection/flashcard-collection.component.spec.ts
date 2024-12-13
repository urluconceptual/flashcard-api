import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlashcardCollectionComponent } from './flashcard-collection.component';

describe('FlashcardCollectionComponent', () => {
  let component: FlashcardCollectionComponent;
  let fixture: ComponentFixture<FlashcardCollectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlashcardCollectionComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(FlashcardCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
