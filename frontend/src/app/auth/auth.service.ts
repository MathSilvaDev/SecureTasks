import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginResponse } from './model/login-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private readonly API_URL = '/api/auth';

  constructor(private http: HttpClient) { }

  register(username: string, email: string, password: string): Observable<void> {
    return this.http.post<void>(`${this.API_URL}/register`, { 
      username, 
      email, 
      password 
    });
  }

  login(email: string, password: string): Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${this.API_URL}/login`, {
      email,
      password
    })
  }

  setToken(token: string, expiresAt: number): void {
    if (!token || token === 'undefined' || token === 'null') {
      return;
    }

    localStorage.setItem('authToken', token);
    localStorage.setItem('tokenExpiresAt', expiresAt.toString());
  }

  getToken(): string | null {
    const token = localStorage.getItem('authToken');

    if (!token || token === 'undefined' || token === 'null') {
      return null;
    }
    return token;
  }

  clearToken(): void {
    localStorage.removeItem('authToken');
    localStorage.removeItem('tokenExpiresAt');
  }

  clearAll(): void {
    localStorage.clear();
  }
}
