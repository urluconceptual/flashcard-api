import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CollectionDialog} from './collection-dialog';

describe('NewCollectionDialogComponent', () => {
  let component: CollectionDialog;
  let fixture: ComponentFixture<CollectionDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollectionDialog]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CollectionDialog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
