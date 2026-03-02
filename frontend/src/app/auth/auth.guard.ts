import { inject } from "@angular/core";
import { CanActivateFn } from "@angular/router";
import { NavigateService } from "../navigate.service";

export const authGuard: CanActivateFn = () => {
  const token = localStorage.getItem('authToken');
  const navigateService = inject(NavigateService)

  if(!token){
    navigateService.goToLogin();
    return false;
  }
  return true;
}