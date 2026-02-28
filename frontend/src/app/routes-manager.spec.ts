import { TestBed } from '@angular/core/testing';

import { RoutesManager } from './routes-manager';

describe('RoutesManager', () => {
  let service: RoutesManager;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoutesManager);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
