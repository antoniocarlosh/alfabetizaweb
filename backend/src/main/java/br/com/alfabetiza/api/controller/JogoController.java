package br.com.alfabetiza.api.controller;

import br.com.alfabetiza.api.dto.*;
import br.com.alfabetiza.api.model.Resultado;
import br.com.alfabetiza.api.service.AcessoFamiliaService;
import br.com.alfabetiza.api.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JogoController {
    private final JogoService service;
    private final AcessoFamiliaService acesso;

    public JogoController(JogoService service, AcessoFamiliaService acesso) {
        this.service = service;
        this.acesso = acesso;
    }

    public record ResultadoPublico(
        Long id, Long alunoId, Long atividadeId, String resposta,
        boolean correto, int pontosObtidos, LocalDateTime realizadoEm
    ) {
        static ResultadoPublico de(Resultado resultado) {
            return new ResultadoPublico(
                resultado.getId(), resultado.getAluno().getId(), resultado.getAtividade().getId(),
                resultado.getResposta(), resultado.getCorreto(), resultado.getPontosObtidos(),
                resultado.getRealizadoEm()
            );
        }
    }

    @PostMapping("/respostas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultadoPublico responder(@Valid @RequestBody RespostaRequest request, Authentication auth) {
        acesso.alunoAutorizado(request.alunoId(), auth);
        return ResultadoPublico.de(service.responder(request));
    }

    @GetMapping("/alunos/{id}/resultados")
    public List<ResultadoPublico> resultados(@PathVariable Long id, Authentication auth) {
        acesso.alunoAutorizado(id, auth);
        return service.historico(id).stream().map(ResultadoPublico::de).toList();
    }

    @GetMapping("/alunos/{id}/progresso")
    public ProgressoResponse progresso(@PathVariable Long id, Authentication auth) {
        acesso.alunoAutorizado(id, auth);
        return service.progresso(id);
    }
}
