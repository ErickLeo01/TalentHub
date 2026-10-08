import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CompanyUpdate } from './company-update';

describe('CompanyUpdate', () => {
  let component: CompanyUpdate;
  let fixture: ComponentFixture<CompanyUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompanyUpdate],
    }).compileComponents();

    fixture = TestBed.createComponent(CompanyUpdate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
