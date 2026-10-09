import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CandidateDelete } from './candidate-delete';

describe('CandidateDelete', () => {
  let component: CandidateDelete;
  let fixture: ComponentFixture<CandidateDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CandidateDelete],
    }).compileComponents();

    fixture = TestBed.createComponent(CandidateDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
