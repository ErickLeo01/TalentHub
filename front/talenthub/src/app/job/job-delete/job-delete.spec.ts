import { ComponentFixture, TestBed } from '@angular/core/testing';
import { JobDelete } from './job-delete';

describe('JobDelete', () => {
  let component: JobDelete;
  let fixture: ComponentFixture<JobDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobDelete],
    }).compileComponents();

    fixture = TestBed.createComponent(JobDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
