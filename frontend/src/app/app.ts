import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs';
import { AlfabetizaApiService, Aluno, Atividade, Progresso, Responsavel, PainelModalidade } from './alfabetiza-api.service';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [CommonModule, FormsModule, RouterOutlet],
    templateUrl: './app.html',
    styleUrl: './app.css'
})
export class App implements OnInit {
    private readonly api = inject(AlfabetizaApiService);
    private readonly cdr = inject(ChangeDetectorRef);
    private readonly router = inject(Router);
    private readonly chaveRodada = 'alfabetiza-v2-rodada';
    private readonly chaveAluno = 'alfabetiza-v2-aluno';

    alunos: Aluno[] = [];
    conta: Responsavel | null = null;
    verificandoSessao = true;
    modoAcesso: 'entrar' | 'registrar' = 'entrar';
    emailAcesso = '';
    senhaAcesso = '';
    nomeAcesso = '';
    processandoAcesso = false;
    paineis: PainelModalidade[] = [];
    filtroHistorico = "LETRAS";
    rodadaId: number | null = null;
    alunoSelecionadoId: number | null = null;
    nomeAluno = '';
    idadeAluno = 7;
    atividades: Atividade[] = [];
    indiceAtividade = 0;
    modulo = '';
    respostaSelecionada = '';
    resultado: 'acerto' | 'erro' | '' = '';
    progresso: Progresso | null = null;
    carregando = false;
    enviando = false;
    mensagemErro = '';
    tela: 'inicio' | 'jogo' | 'progresso' = 'inicio';

    get alunoAtual(): Aluno | undefined {
        return this.alunos.find(aluno => aluno.id === Number(this.alunoSelecionadoId));
    }

    get atividadeAtual(): Atividade | undefined {
        return this.atividades[this.indiceAtividade];
    }

    get finalizado(): boolean {
        return this.tela === 'jogo' && this.atividades.length > 0 && this.indiceAtividade >= this.atividades.length;
    }

    ngOnInit(): void {
        this.router.events.pipe(filter(evento => evento instanceof NavigationEnd)).subscribe(() => {
            this.sincronizarRota();
        });
        this.api.buscarConta().subscribe({
            next: conta => {
                this.conta = conta;
                this.verificandoSessao = false;
                this.carregarCadastros();
                this.sincronizarRota();
                this.cdr.markForCheck();
            },
            error: () => {
                this.verificandoSessao = false;
                this.router.navigateByUrl('/login', { replaceUrl: true });
                this.cdr.markForCheck();
            }
        });
    }

    autenticar(): void {
        if (!this.emailAcesso.trim() || !this.senhaAcesso) {
            this.mensagemErro = 'Informe e-mail e senha.';
            return;
        }

        if (this.modoAcesso === 'registrar' && !this.nomeAcesso.trim()) {
            this.mensagemErro = 'Informe seu nome.';
            return;
        }

        this.processandoAcesso = true;
        this.mensagemErro = '';
        const requisicao = this.modoAcesso === 'entrar'
            ? this.api.entrar(this.emailAcesso.trim(), this.senhaAcesso)
            : this.api.registrar(this.nomeAcesso.trim(), this.emailAcesso.trim(), this.senhaAcesso);

        requisicao.subscribe({
            next: conta => {
                this.processandoAcesso = false;
                this.senhaAcesso = '';
                if (this.modoAcesso === 'registrar') {
                    this.modoAcesso = 'entrar';
                    this.mensagemErro = 'Conta criada! Agora entre com seu e-mail e senha.';
                } else {
                    this.conta = conta;
                    this.carregarCadastros();
                    this.router.navigateByUrl('/inicio', { replaceUrl: true });
                }
                this.cdr.markForCheck();
            },
            error: erro => {
                this.processandoAcesso = false;
                this.mensagemErro = erro.status === 409 ? 'Este e-mail já está cadastrado.'
                    : erro.status === 401 ? 'E-mail ou senha incorretos.'
                    : 'Não foi possível concluir o acesso.';
                this.cdr.markForCheck();
            }
        });
    }

    sair(): void {
        this.api.sair().subscribe({
            next: () => {
                this.conta = null;
                this.alunos = [];
                this.alunoSelecionadoId = null;
                this.tela = 'inicio';
                this.limparRodadaSalva();
                sessionStorage.removeItem(this.chaveAluno);
                this.router.navigateByUrl('/login', { replaceUrl: true });
                this.mensagemErro = '';
                this.cdr.markForCheck();
            },
            error: () => {
                this.mensagemErro = 'Não foi possível encerrar a sessão.';
                this.cdr.markForCheck();
            }
        });
    }

    carregarCadastros(): void {
        this.api.listarAlunos().subscribe({
            next: alunos => {
                this.alunos = alunos;
                if (!alunos.some(aluno => aluno.id === this.alunoSelecionadoId)) {
                    this.alunoSelecionadoId = alunos.length > 0 ? alunos[0].id : null;
                }
                const salvo = Number(sessionStorage.getItem(this.chaveAluno));
                if (alunos.some(aluno => aluno.id === salvo)) {
                    this.alunoSelecionadoId = salvo;
                }
                this.sincronizarRota();
                this.cdr.markForCheck();
            },
            error: () => {
                this.mensagemErro = 'Não foi possível carregar os alunos da família.';
                this.cdr.markForCheck();
            }
        });
    }

    excluirAluno(aluno: Aluno): void {
        if (!window.confirm(`Excluir ${aluno.nome} e seu histórico de respostas? Esta ação não pode ser desfeita.`)) {
            return;
        }

        this.api.excluirAluno(aluno.id).subscribe({
            next: () => {
                this.carregarCadastros();
                this.mensagemErro = '';
                this.cdr.markForCheck();
            },
            error: () => {
                this.mensagemErro = 'Não foi possível excluir o aluno.';
                this.cdr.markForCheck();
            }
        });
    }

    cadastrarAluno(): void {
        if (!this.nomeAluno.trim() || this.idadeAluno < 3 || this.idadeAluno > 18) {
            this.mensagemErro = 'Informe um nome e uma idade entre 3 e 18 anos.';
            return;
        }

        this.mensagemErro = '';
        this.api.cadastrarAluno(this.nomeAluno.trim(), this.idadeAluno).subscribe({
            next: aluno => {
                this.alunos.push(aluno);
                this.alunoSelecionadoId = aluno.id;
                sessionStorage.setItem(this.chaveAluno, String(aluno.id));
                this.nomeAluno = '';
                this.cdr.markForCheck();
            },
            error: () => {
                this.mensagemErro = 'Erro ao cadastrar aluno.';
                this.cdr.markForCheck();
            }
        });
    }

    iniciarModulo(tipo: string): void {
        if (this.alunoSelecionadoId === null) {
            this.mensagemErro = 'Selecione ou cadastre um aluno primeiro.';
            return;
        }

        this.limparRodadaSalva();
        sessionStorage.setItem(this.chaveAluno, String(this.alunoSelecionadoId));
        this.modulo = tipo;
        this.carregando = true;
        this.mensagemErro = '';
        this.api.iniciarRodada(this.alunoSelecionadoId, tipo).subscribe({
            next: inicio => {
                this.rodadaId = inicio.rodadaId;
                this.atividades = inicio.atividades;
                this.indiceAtividade = 0;
                this.respostaSelecionada = '';
                this.resultado = '';
                this.carregando = false;
                this.tela = 'jogo';
                this.salvarRodada();
                this.router.navigateByUrl(this.rotaJogo(tipo));
                this.cdr.markForCheck();
            },
            error: () => {
                this.carregando = false;
                this.mensagemErro = 'Não foi possível carregar as atividades.';
                this.cdr.markForCheck();
            }
        });
    }

    escolherResposta(opcao: string): void {
        if (this.resultado || this.enviando || !this.atividadeAtual || this.alunoSelecionadoId === null || this.rodadaId === null) {
            return;
        }

        this.enviando = true;
        this.mensagemErro = '';
        this.respostaSelecionada = opcao;
        this.falar(opcao);
        this.api.responderRodada(this.rodadaId, this.alunoSelecionadoId, this.atividadeAtual.id, opcao).subscribe({
            next: resultado => {
                this.resultado = resultado.correto ? 'acerto' : 'erro';
                this.salvarRodada();
                this.enviando = false;
                this.cdr.markForCheck();
            },
            error: () => {
                this.enviando = false;
                this.respostaSelecionada = '';
                this.mensagemErro = 'Não foi possível registrar a resposta. Tente novamente.';
                this.cdr.markForCheck();
            }
        });
    }

    proximaAtividade(): void {
        this.indiceAtividade++;
        this.resultado = '';
        this.respostaSelecionada = '';
        this.salvarRodada();
        this.cdr.markForCheck();
    }

    abrirProgresso(): void {
        if (this.alunoSelecionadoId === null) {
            this.mensagemErro = 'Selecione um aluno.';
            return;
        }

        this.api.buscarPainelRodadas(this.alunoSelecionadoId).subscribe({
            next: paineis => {
                this.paineis = paineis;
                this.tela = 'progresso';
                this.router.navigateByUrl('/progresso');
                this.mensagemErro = '';
                this.cdr.markForCheck();
            },
            error: () => {
                this.mensagemErro = 'Não foi possível carregar os recordes.';
                this.cdr.markForCheck();
            }
        });
    }


    ouvirEnunciado(): void {
        const atividade = this.atividadeAtual;

        if (atividade) {
            this.falar(atividade.enunciado);
        }
    }

    private falar(texto: string): void {
        if (typeof window === 'undefined' || !('speechSynthesis' in window)) {
            this.mensagemErro = 'Seu navegador não oferece leitura em voz alta.';
            this.cdr.markForCheck();
            return;
        }

        window.speechSynthesis.cancel();

        const fala = new SpeechSynthesisUtterance(texto);
        fala.lang = 'pt-BR';
        fala.rate = 0.85;

        const vozPortugues = window.speechSynthesis.getVoices().find(voz =>
            voz.lang.toLowerCase().startsWith('pt-br')
        );

        if (vozPortugues) {
            fala.voice = vozPortugues;
        }

        window.speechSynthesis.speak(fala);
    }

    private embaralhar<T>(itens: T[]): T[] {
        for (let indice = itens.length - 1; indice > 0; indice--) {
            const sorteado = Math.floor(Math.random() * (indice + 1));
            [itens[indice], itens[sorteado]] = [itens[sorteado], itens[indice]];
        }

        return itens;
    }

    nomeModalidade(tipo: string): string {
        if (tipo === 'LETRAS') return 'Letras';
        if (tipo === 'SILABAS') return 'Sílabas';
        return 'Imagem e palavra';
    }

    get painelSelecionado(): PainelModalidade | undefined {
        return this.paineis.find(painel => painel.modalidade === this.filtroHistorico);
    }

    voltarInicio(): void {
        this.tela = 'inicio';
        this.resultado = '';
        this.mensagemErro = '';
        this.router.navigateByUrl('/inicio');
    }

    selecionarAluno(id: number | null): void {
        this.alunoSelecionadoId = id;
        if (id !== null) {
            sessionStorage.setItem(this.chaveAluno, String(id));
        } else {
            sessionStorage.removeItem(this.chaveAluno);
        }
        this.limparRodadaSalva();
    }

    private rotaJogo(tipo: string): string {
        if (tipo === 'LETRAS') return '/jogo/letras';
        if (tipo === 'SILABAS') return '/jogo/silabas';
        return '/jogo/imagens';
    }

    private sincronizarRota(): void {
        if (this.verificandoSessao) return;
        if (!this.conta) {
            if (this.router.url !== '/login') {
                this.router.navigateByUrl('/login', { replaceUrl: true });
            }
            return;
        }

        const rota = this.router.url.split('?')[0];
        if (rota === '/login') {
            this.router.navigateByUrl('/inicio', { replaceUrl: true });
            return;
        }
        if (rota === '/progresso') {
            this.tela = 'progresso';
            if (this.alunoSelecionadoId !== null) {
                this.api.buscarPainelRodadas(this.alunoSelecionadoId).subscribe({
                    next: paineis => {
                        this.paineis = paineis;
                        this.cdr.markForCheck();
                    },
                    error: () => {
                        this.mensagemErro = 'Não foi possível carregar os recordes.';
                        this.cdr.markForCheck();
                    }
                });
            }
        } else if (rota.startsWith('/jogo/')) {
            if (!this.restaurarRodada(rota)) {
                this.tela = 'inicio';
                this.mensagemErro = 'A rodada não está disponível neste navegador. Inicie uma nova partida.';
                this.router.navigateByUrl('/inicio', { replaceUrl: true });
                return;
            }
            this.tela = 'jogo';
        } else {
            this.tela = 'inicio';
        }
        this.cdr.markForCheck();
    }

    private salvarRodada(): void {
        if (!this.conta || this.alunoSelecionadoId === null || this.rodadaId === null) return;
        const estado = {
            responsavelId: this.conta.id,
            alunoId: this.alunoSelecionadoId,
            rodadaId: this.rodadaId,
            modulo: this.modulo,
            atividades: this.atividades,
            indiceAtividade: this.indiceAtividade,
            respostaSelecionada: this.respostaSelecionada,
            resultado: this.resultado
        };
        sessionStorage.setItem(this.chaveRodada, JSON.stringify(estado));
    }

    private restaurarRodada(rota: string): boolean {
        try {
            const texto = sessionStorage.getItem(this.chaveRodada);
            if (!texto || !this.conta) return false;
            const estado = JSON.parse(texto);
            if (estado.responsavelId !== this.conta.id ||
                !Number.isInteger(estado.alunoId) ||
                !Number.isInteger(estado.rodadaId) ||
                !Array.isArray(estado.atividades) ||
                !Number.isInteger(estado.indiceAtividade) ||
                estado.indiceAtividade < 0 ||
                estado.indiceAtividade > estado.atividades.length ||
                this.rotaJogo(estado.modulo) !== rota) return false;
            if (this.alunos.length && !this.alunos.some(aluno => aluno.id === estado.alunoId)) return false;
            this.alunoSelecionadoId = estado.alunoId;
            this.rodadaId = estado.rodadaId;
            this.modulo = estado.modulo;
            this.atividades = estado.atividades;
            this.indiceAtividade = estado.indiceAtividade;
            this.respostaSelecionada = estado.respostaSelecionada || '';
            this.resultado = estado.resultado || '';
            return true;
        } catch {
            return false;
        }
    }

    private limparRodadaSalva(): void {
        sessionStorage.removeItem(this.chaveRodada);
    }
}
