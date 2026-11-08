import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, switchMap, tap } from 'rxjs';

export interface Responsavel { id: number; nome: string; email: string; }
export interface Aluno { id: number; nome: string; idade: number; responsavel?: Responsavel | null; }
export interface Atividade { id: number; titulo: string; tipo: string; enunciado: string; opcoes: string[]; pontos: number; imagemUrl?: string | null; }
export interface Resultado { id: number; alunoId: number; atividadeId: number; resposta: string; correto: boolean; pontosObtidos: number; realizadoEm: string; }
export interface Progresso { alunoId: number; atividadesRespondidas: number; acertos: number; pontosTotais: number; percentualAcerto: number; }

export interface ResumoRodada {
    id: number;
    modalidade: string;
    acertos: number;
    erros: number;
    pontos: number;
    total: number;
    concluidaEm: string;
}
export interface PainelModalidade {
    modalidade: string;
    recorde: ResumoRodada | null;
    historico: ResumoRodada[];
}
export interface InicioRodada {
    rodadaId: number;
    modalidade: string;
    atividades: Atividade[];
}
export interface RespostaRodada {
    correto: boolean;
    pontosObtidos: number;
    rodadaConcluida: boolean;
}

@Injectable({ providedIn: 'root' })
export class AlfabetizaApiService {
    private readonly http = inject(HttpClient);
    private readonly baseUrl = '/api';
    private csrfToken = '';

    prepararCsrf(): Observable<{ token: string }> {
        return this.http.get<{ token: string }>(`${this.baseUrl}/auth/csrf`).pipe(
            tap(resposta => this.csrfToken = resposta.token)
        );
    }

    private cabecalhos(): HttpHeaders {
        return new HttpHeaders({ 'X-XSRF-TOKEN': this.csrfToken });
    }

    private postSeguro<T>(rota: string, corpo: object): Observable<T> {
        return this.prepararCsrf().pipe(
            switchMap(() => this.http.post<T>(`${this.baseUrl}${rota}`, corpo, { headers: this.cabecalhos() }))
        );
    }

    buscarConta(): Observable<Responsavel> {
        return this.http.get<Responsavel>(`${this.baseUrl}/auth/me`);
    }

    registrar(nome: string, email: string, senha: string): Observable<Responsavel> {
        return this.postSeguro<Responsavel>('/auth/registrar', { nome, email, senha });
    }

    entrar(email: string, senha: string): Observable<Responsavel> {
        return this.postSeguro<Responsavel>('/auth/login', { email, senha });
    }

    sair(): Observable<void> {
        return this.postSeguro<void>('/auth/sair', {});
    }

    listarAlunos(): Observable<Aluno[]> {
        return this.http.get<Aluno[]>(`${this.baseUrl}/alunos`);
    }

    cadastrarAluno(nome: string, idade: number): Observable<Aluno> {
        return this.postSeguro<Aluno>('/alunos', { nome, idade });
    }

    excluirAluno(id: number): Observable<void> {
        return this.prepararCsrf().pipe(
            switchMap(() => this.http.delete<void>(`${this.baseUrl}/alunos/${id}`, { headers: this.cabecalhos() }))
        );
    }

    listarAtividades(tipo: string): Observable<Atividade[]> {
        return this.http.get<Atividade[]>(`${this.baseUrl}/atividades`, { params: { tipo } });
    }

    responder(alunoId: number, atividadeId: number, resposta: string): Observable<Resultado> {
        return this.postSeguro<Resultado>('/respostas', { alunoId, atividadeId, resposta });
    }

    buscarProgresso(alunoId: number): Observable<Progresso> {
        return this.http.get<Progresso>(`${this.baseUrl}/alunos/${alunoId}/progresso`);
    }

    buscarResultados(alunoId: number): Observable<Resultado[]> {
        return this.http.get<Resultado[]>(`${this.baseUrl}/alunos/${alunoId}/resultados`);
    }
    iniciarRodada(alunoId: number, modalidade: string): Observable<InicioRodada> {
        return this.postSeguro<InicioRodada>('/rodadas', { alunoId, modalidade });
    }

    responderRodada(rodadaId: number, alunoId: number, atividadeId: number,
                    resposta: string): Observable<RespostaRodada> {
        return this.postSeguro<RespostaRodada>(`/rodadas/${rodadaId}/respostas`,
            { alunoId, atividadeId, resposta });
    }

    buscarPainelRodadas(alunoId: number): Observable<PainelModalidade[]> {
        return this.http.get<PainelModalidade[]>(`${this.baseUrl}/alunos/${alunoId}/rodadas`);
    }

}
