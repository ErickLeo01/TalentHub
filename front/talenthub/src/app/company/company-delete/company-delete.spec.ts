import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CompanyDelete } from './company-delete';

describe('CompanyDelete', () => {
  let component: CompanyDelete;
  let fixture: ComponentFixture<CompanyDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompanyDelete],
    }).compileComponents();

    fixture = TestBed.createComponent(CompanyDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
