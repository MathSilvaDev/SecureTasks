import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";
import { AuthService } from '../auth/auth.service';
import { NavigateService } from '../navigate.service';

@Component({
  selector: 'app-register',
  imports: [RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {

  email: string = '';
  username: string = '';
  password: string = '';

  constructor(
    private authService: AuthService,
    private navigateService: NavigateService
  ) {}

  register() {
    const email = this.email.trim();
    const username = this.username.trim();
    const password = this.password.trim();

    this.authService.register(email, username, password).subscribe({
      next: (response) => {
        console.log('Registration successful:', response);
        this.navigateService.goToLogin();
      },
      error: (err) => {
        console.error('Registration failed:', err);
      } 
    })
  }

  
}
