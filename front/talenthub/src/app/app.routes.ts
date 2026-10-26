import { Routes } from '@angular/router';

// Candidato
import { CandidateList } from './candidate/candidate-list/candidate-list';
import { CandidateCreate } from './candidate/candidate-create/candidate-create';
import { CandidateUpdate } from './candidate/candidate-update/candidate-update';
import { CandidateDelete } from './candidate/candidate-delete/candidate-delete';
import { CandidateLogin } from './candidate/candidate-login/candidate-login';
import { CandidateProfile } from './candidate/candidate-profile/candidate-profile';

// Empresa
import { CompanyCreate } from './company/company-create/company-create';
import { CompanyLogin } from './company/company-login/company-login';
import { CompanyProfile } from './company/company-profile/company-profile';
import { CompanyUpdate } from './company/company-update/company-update';
import { CompanyDelete } from './company/company-delete/company-delete';

// Vagas
import { JobList } from './job/job-list/job-list';
import { JobCreate } from './job/job-create/job-create';
import { JobUpdate } from './job/job-update/job-update';
import { JobDelete } from './job/job-delete/job-delete';
import { JobDetails } from './job/job-details/job-details';

import { authGuard } from './auth-guard';

export const routes: Routes = [

  // Candidato
  {
    path: 'candidato/login',
    component: CandidateLogin
  },
  {
    path: 'candidato/criar',
    component: CandidateCreate
  },
  {
    path: 'candidato/perfil',
    component: CandidateProfile,
    canActivate: [authGuard]
  },
  {
    path: 'candidato/atualizar/:id',
    component: CandidateUpdate,
    //canActivate: [authGuard]
  },
  {
    path: 'candidato/deletar/:id',
    component: CandidateDelete,
    canActivate: [authGuard]
  },
  {
    path: 'candidatos',
    component: CandidateList,
    canActivate: [authGuard]
  },

  // Empresa
  {
    path: 'empresa/login',
    component: CompanyLogin
  },
  {
    path: 'empresa/criar',
    component: CompanyCreate
  },
  {
    path: 'empresa/perfil',
    component: CompanyProfile,
    canActivate: [authGuard]
  },
  {
    path: 'empresa/atualizar/:id',
    component: CompanyUpdate,
    canActivate: [authGuard]
  },
  {
    path: 'empresa/deletar/:id',
    component: CompanyDelete,
    canActivate: [authGuard]
  },

  // Vagas
  {
    path: 'vagas',
    component: JobList,
    canActivate: [authGuard]
  },
  {
    path: 'vagas/criar',
    component: JobCreate,
    canActivate: [authGuard]
  },
  {
    path: 'vagas/atualizar/:id',
    component: JobUpdate,
    canActivate: [authGuard]
  },
  {
    path: 'vagas/deletar/:id',
    component: JobDelete,
    canActivate: [authGuard]
  },
  {
    path: 'vagas/detalhes/:id',
    component: JobDetails,
    canActivate: [authGuard]
  },

  // Rotas padrão
  {
    path: '',
    redirectTo: 'candidato/login',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: 'candidato/login'
  }
];
