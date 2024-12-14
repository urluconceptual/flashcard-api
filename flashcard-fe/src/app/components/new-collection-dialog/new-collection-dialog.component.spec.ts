import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCollectionDialogComponent } from './new-collection-dialog.component';

describe('NewCollectionDialogComponent', () => {
  let component: NewCollectionDialogComponent;
  let fixture: ComponentFixture<NewCollectionDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewCollectionDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewCollectionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
