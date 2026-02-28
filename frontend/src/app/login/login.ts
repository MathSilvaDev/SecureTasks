import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from "@angular/router";
import { AuthService } from '../auth/auth.service';
import { NavigateService } from '../navigate.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  email: string = '';
  password: string = '';

  constructor(
    private authService: AuthService, 
    private navigateService: NavigateService
  ) {}

  login() {
    const email = this.email.trim();
    const password = this.password.trim();

    this.authService.login(email, password).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        this.navigateService.goToHome();
      },
      error: (err) => {
        console.error('Login failed:', err)
      }
    });
  }
  
}
