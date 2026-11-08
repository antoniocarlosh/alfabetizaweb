package br.com.alfabetiza.api.controller;

import br.com.alfabetiza.api.model.Responsavel;
import br.com.alfabetiza.api.repository.ResponsavelRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public record RegistrarRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 72) String senha
    ) {
    }

    public record LoginRequest(@NotBlank String email, @NotBlank String senha) {
    }

    public record Conta(Long id, String nome, String email) {
        public static Conta de(Responsavel responsavel) {
            return new Conta(responsavel.getId(), responsavel.getNome(), responsavel.getEmail());
        }
    }

    public record UsuarioAutenticado(Long id) {
    }

    private final ResponsavelRepository responsaveis;
    private final PasswordEncoder encoder;
    private final HttpSessionSecurityContextRepository contextRepository =
        new HttpSessionSecurityContextRepository();

    public AuthController(ResponsavelRepository responsaveis, PasswordEncoder encoder) {
        this.responsaveis = responsaveis;
        this.encoder = encoder;
    }

    @GetMapping("/csrf")
    public java.util.Map<String, String> csrf(CsrfToken token) {
        return java.util.Map.of("token", token.getToken());
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta registrar(@Valid @RequestBody RegistrarRequest request) {
        String email = request.email().trim().toLowerCase(Locale.ROOT);

        if (responsaveis.existsByEmailIgnoreCase(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado ou pendente de migração");
        }

        Responsavel responsavel = new Responsavel();
        responsavel.setNome(request.nome().trim());
        responsavel.setEmail(email);
        responsavel.setSenhaHash(encoder.encode(request.senha()));

        return Conta.de(responsaveis.save(responsavel));
    }

    @PostMapping("/login")
    public Conta login(
        @Valid @RequestBody LoginRequest request,
        HttpServletRequest servletRequest,
        HttpServletResponse servletResponse
    ) {
        Responsavel responsavel = responsaveis.findByEmailIgnoreCase(request.email().trim())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));

        if (responsavel.getSenhaHash() == null ||
            !encoder.matches(request.senha(), responsavel.getSenhaHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        servletRequest.getSession(true).invalidate();
        servletRequest.getSession(true);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            new UsuarioAutenticado(responsavel.getId()), null, List.of()
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        contextRepository.saveContext(context, servletRequest, servletResponse);

        return Conta.de(responsavel);
    }

    @GetMapping("/me")
    public Conta me(Authentication authentication) {
        Long id = ((UsuarioAutenticado) authentication.getPrincipal()).id();
        Responsavel responsavel = responsaveis.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return Conta.de(responsavel);
    }

    @PostMapping("/sair")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sair(HttpServletRequest request) {
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
        SecurityContextHolder.clearContext();
    }
}
