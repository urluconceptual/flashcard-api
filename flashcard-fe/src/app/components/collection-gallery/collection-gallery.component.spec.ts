import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectionGalleryComponent } from './collection-gallery.component';

describe('CollectionGalleryComponent', () => {
  let component: CollectionGalleryComponent;
  let fixture: ComponentFixture<CollectionGalleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollectionGalleryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollectionGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
