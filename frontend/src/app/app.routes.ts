import { Component } from '@angular/core';
import { Routes } from '@angular/router';

// O componente principal mantém o layout; as rotas controlam a URL e o histórico.
@Component({ standalone: true, template: '' })
export class PaginaRota {}

export const routes: Routes = [
    { path: 'login', component: PaginaRota },
    { path: 'inicio', component: PaginaRota },
    { path: 'progresso', component: PaginaRota },
    { path: 'jogo/letras', component: PaginaRota },
    { path: 'jogo/silabas', component: PaginaRota },
    { path: 'jogo/imagens', component: PaginaRota },
    { path: '', pathMatch: 'full', redirectTo: 'inicio' },
    { path: '**', redirectTo: 'inicio' }
];
