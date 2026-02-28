import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginResponse } from './model/login-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private readonly API_URL = '/api/auth';

  constructor(private httpClient: HttpClient) { }

  register(username: string, email: string, password: string): Observable<void> {
    return this.httpClient.post<void>(`${this.API_URL}/register`, { 
      username, 
      email, 
      password 
    });
  }

  login(email: string, password: string): Observable<LoginResponse>{
    return this.httpClient.post<LoginResponse>(`${this.API_URL}/login`, {
      email,
      password
    })
  }

  setToken(token: string, expiresAt: number): void {
    localStorage.setItem('authToken', token);
    localStorage.setItem('tokenExpiresAt', expiresAt.toString());
  }

  getToken(): string | null {
    const token = localStorage.getItem('authToken');
    return token;
  }

  clearToken(): void {
    localStorage.removeItem('authToken');
  }

  clearAll(): void {
    localStorage.clear();
  }
}
