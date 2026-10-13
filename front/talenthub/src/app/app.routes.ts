import { Routes } from '@angular/router';
import { CandidateList } from './candidate/candidate-list/candidate-list';
import { CandidateUpdate } from './candidate/candidate-update/candidate-update';
import { CandidateLogin } from './candidate/candidate-login/candidate-login';
import { authGuard } from './auth-guard';

export const routes: Routes = [

  {
    path: 'candidato/login',
    component: CandidateLogin
  },

  {
    path: 'candidatos',
    component: CandidateList,
    canActivate: [authGuard]
  },

  {
    path: 'candidatos/update/:id',
    component: CandidateUpdate,
    canActivate: [authGuard]
  },

  {
    path: '',
    redirectTo: '/candidato/login',
    pathMatch: 'full'
  }
];
