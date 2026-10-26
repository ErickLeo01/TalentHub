import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  // Se estiver rodando no servidor (SSR), permite passar para que o Angular instancie o componente no cliente
  if (!isPlatformBrowser(platformId)) {
    return true;
  }

  const token = localStorage.getItem('token');

  if (token && token.trim() !== '' && token !== 'null' && token !== 'undefined') {
    return true;
  }

  return router.createUrlTree(['/candidato/login']);
};
