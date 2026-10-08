import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CandidateUpdate } from './candidate-update';

describe('CandidateUpdate', () => {
  let component: CandidateUpdate;
  let fixture: ComponentFixture<CandidateUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CandidateUpdate],
    }).compileComponents();

    fixture = TestBed.createComponent(CandidateUpdate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
