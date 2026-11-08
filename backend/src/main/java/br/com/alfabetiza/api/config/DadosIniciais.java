package br.com.alfabetiza.api.config;

import br.com.alfabetiza.api.model.Atividade;
import br.com.alfabetiza.api.repository.AtividadeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DadosIniciais {

    @Bean
    CommandLineRunner carregarAtividades(AtividadeRepository repository) {
        return args -> {
            Set<String> titulosExistentes = new HashSet<>();

            for (Atividade atividade : repository.findAll()) {
                titulosExistentes.add(atividade.getTitulo());
            }

            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra A", "LETRAS", "Qual é a letra A?", "A|B|C", "A", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra B", "LETRAS", "Qual é a letra B?", "B|C|D", "B", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra C", "LETRAS", "Qual é a letra C?", "C|D|E", "C", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra D", "LETRAS", "Qual é a letra D?", "D|E|F", "D", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra E", "LETRAS", "Qual é a letra E?", "E|F|G", "E", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra F", "LETRAS", "Qual é a letra F?", "F|G|H", "F", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra G", "LETRAS", "Qual é a letra G?", "G|H|I", "G", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra H", "LETRAS", "Qual é a letra H?", "H|I|J", "H", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra I", "LETRAS", "Qual é a letra I?", "I|J|K", "I", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra J", "LETRAS", "Qual é a letra J?", "J|K|L", "J", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra K", "LETRAS", "Qual é a letra K?", "K|L|M", "K", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra L", "LETRAS", "Qual é a letra L?", "L|M|N", "L", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra M", "LETRAS", "Qual é a letra M?", "M|N|O", "M", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra N", "LETRAS", "Qual é a letra N?", "N|O|P", "N", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra O", "LETRAS", "Qual é a letra O?", "O|P|Q", "O", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra P", "LETRAS", "Qual é a letra P?", "P|Q|R", "P", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra Q", "LETRAS", "Qual é a letra Q?", "Q|R|S", "Q", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra R", "LETRAS", "Qual é a letra R?", "R|S|T", "R", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra S", "LETRAS", "Qual é a letra S?", "S|T|U", "S", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra T", "LETRAS", "Qual é a letra T?", "T|U|V", "T", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra U", "LETRAS", "Qual é a letra U?", "U|V|W", "U", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra V", "LETRAS", "Qual é a letra V?", "V|W|X", "V", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra W", "LETRAS", "Qual é a letra W?", "W|X|Y", "W", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra X", "LETRAS", "Qual é a letra X?", "X|Y|Z", "X", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra Y", "LETRAS", "Qual é a letra Y?", "Y|Z|A", "Y", null);
            criarSeNaoExistir(repository, titulosExistentes, "Identifique a letra Z", "LETRAS", "Qual é a letra Z?", "Z|A|B", "Z", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: CASA", "SILABAS", "Qual é a primeira sílaba de CASA?", "CA|SA|MA", "CA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: BOLA", "SILABAS", "Qual é a primeira sílaba de BOLA?", "BO|LA|MA", "BO", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: GATO", "SILABAS", "Qual é a primeira sílaba de GATO?", "GA|TO|MA", "GA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: PATO", "SILABAS", "Qual é a primeira sílaba de PATO?", "PA|TO|MA", "PA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: SAPO", "SILABAS", "Qual é a primeira sílaba de SAPO?", "SA|PO|MA", "SA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: MALA", "SILABAS", "Qual é a primeira sílaba de MALA?", "MA|LA|MA", "MA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: BOLO", "SILABAS", "Qual é a primeira sílaba de BOLO?", "BO|LO|MA", "BO", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: DADO", "SILABAS", "Qual é a primeira sílaba de DADO?", "DA|DO|MA", "DA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: VACA", "SILABAS", "Qual é a primeira sílaba de VACA?", "VA|CA|MA", "VA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: PIPA", "SILABAS", "Qual é a primeira sílaba de PIPA?", "PI|PA|MA", "PI", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: CAMA", "SILABAS", "Qual é a primeira sílaba de CAMA?", "CA|MA|MA", "CA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: FACA", "SILABAS", "Qual é a primeira sílaba de FACA?", "FA|CA|MA", "FA", null);
            criarSeNaoExistir(repository, titulosExistentes, "Sílabas: MESA", "SILABAS", "Qual é a primeira sílaba de MESA?", "ME|SA|MA", "ME", null);
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: BOLA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "BOLA|PATO|DADO", "BOLA", "/assets/imagens/bola.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: CASA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CASA|SAPO|LUA", "CASA", "/assets/imagens/casa.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: GATO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "GATO|MALA|SOL", "GATO", "/assets/imagens/gato.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: PATO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "PATO|BOLO|VACA", "PATO", "/assets/imagens/pato.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: SAPO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "SAPO|DADO|PIPA", "SAPO", "/assets/imagens/sapo.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: MALA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "MALA|LUA|CAMA", "MALA", "/assets/imagens/mala.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: BOLO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "BOLO|SOL|FACA", "BOLO", "/assets/imagens/bolo.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: DADO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "DADO|VACA|MESA", "DADO", "/assets/imagens/dado.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: LUA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "LUA|PIPA|LIVRO", "LUA", "/assets/imagens/lua.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: SOL", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "SOL|CAMA|MAÇÃ", "SOL", "/assets/imagens/sol.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: VACA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "VACA|FACA|BANANA", "VACA", "/assets/imagens/vaca.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: PIPA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "PIPA|MESA|PEIXE", "PIPA", "/assets/imagens/pipa.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: CAMA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CAMA|LIVRO|FLOR", "CAMA", "/assets/imagens/cama.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: FACA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "FACA|MAÇÃ|ÁRVORE", "FACA", "/assets/imagens/faca.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: MESA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "MESA|BANANA|CARRO", "MESA", "/assets/imagens/mesa.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: LIVRO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "LIVRO|PEIXE|BONECA", "LIVRO", "/assets/imagens/livro.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: MAÇÃ", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "MAÇÃ|FLOR|SAPATO", "MAÇÃ", "/assets/imagens/maca.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: BANANA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "BANANA|ÁRVORE|BOLA", "BANANA", "/assets/imagens/banana.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: PEIXE", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "PEIXE|CARRO|CASA", "PEIXE", "/assets/imagens/peixe.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: FLOR", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "FLOR|BONECA|GATO", "FLOR", "/assets/imagens/flor.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: ÁRVORE", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "ÁRVORE|SAPATO|PATO", "ÁRVORE", "/assets/imagens/arvore.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: CARRO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CARRO|BOLA|SAPO", "CARRO", "/assets/imagens/carro.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: BONECA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "BONECA|CASA|MALA", "BONECA", "/assets/imagens/boneca.svg");
            criarSeNaoExistir(repository, titulosExistentes, "Imagem e palavra: SAPATO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "SAPATO|GATO|BOLO", "SAPATO", "/assets/imagens/sapato.svg");
        };
    }

    private void criarSeNaoExistir(
            AtividadeRepository repository,
            Set<String> titulosExistentes,
            String titulo,
            String tipo,
            String enunciado,
            String opcoes,
            String respostaCorreta,
            String imagemUrl) {

        if (titulosExistentes.contains(titulo)) {
            return;
        }

        Atividade atividade = new Atividade();
        atividade.setTitulo(titulo);
        atividade.setTipo(tipo);
        atividade.setEnunciado(enunciado);
        atividade.setOpcoes(opcoes);
        atividade.setRespostaCorreta(respostaCorreta);
        atividade.setImagemUrl(imagemUrl);
        atividade.setPontos(10);

        repository.save(atividade);
        titulosExistentes.add(titulo);
    }
}
