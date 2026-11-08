package br.com.alfabetiza.api.config;

import br.com.alfabetiza.api.model.Atividade;
import br.com.alfabetiza.api.repository.AtividadeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DadosExtras {
    @Bean
    CommandLineRunner adicionarConteudo(AtividadeRepository repository) {
        return args -> {
            Set<String> existentes = repository.findAll().stream()
                    .map(Atividade::getTitulo)
                    .collect(Collectors.toSet());

            adicionar(repository, existentes, "Sílabas INÍCIO: BOLA", "SILABAS", "Qual é a sílaba do início de BOLA?", "BO|LA|MA", "BO", null);
            adicionar(repository, existentes, "Sílabas FIM: BOLA", "SILABAS", "Qual é a sílaba do fim de BOLA?", "LA|BO|MA", "LA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: CASA", "SILABAS", "Qual é a sílaba do início de CASA?", "CA|SA|MA", "CA", null);
            adicionar(repository, existentes, "Sílabas FIM: CASA", "SILABAS", "Qual é a sílaba do fim de CASA?", "SA|CA|MA", "SA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: GATO", "SILABAS", "Qual é a sílaba do início de GATO?", "GA|TO|MA", "GA", null);
            adicionar(repository, existentes, "Sílabas FIM: GATO", "SILABAS", "Qual é a sílaba do fim de GATO?", "TO|GA|MA", "TO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: PATO", "SILABAS", "Qual é a sílaba do início de PATO?", "PA|TO|MA", "PA", null);
            adicionar(repository, existentes, "Sílabas FIM: PATO", "SILABAS", "Qual é a sílaba do fim de PATO?", "TO|PA|MA", "TO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: SAPO", "SILABAS", "Qual é a sílaba do início de SAPO?", "SA|PO|MA", "SA", null);
            adicionar(repository, existentes, "Sílabas FIM: SAPO", "SILABAS", "Qual é a sílaba do fim de SAPO?", "PO|SA|MA", "PO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: MALA", "SILABAS", "Qual é a sílaba do início de MALA?", "MA|LA|PA", "MA", null);
            adicionar(repository, existentes, "Sílabas FIM: MALA", "SILABAS", "Qual é a sílaba do fim de MALA?", "LA|MA|PA", "LA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: BOLO", "SILABAS", "Qual é a sílaba do início de BOLO?", "BO|LO|MA", "BO", null);
            adicionar(repository, existentes, "Sílabas FIM: BOLO", "SILABAS", "Qual é a sílaba do fim de BOLO?", "LO|BO|MA", "LO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: DADO", "SILABAS", "Qual é a sílaba do início de DADO?", "DA|DO|MA", "DA", null);
            adicionar(repository, existentes, "Sílabas FIM: DADO", "SILABAS", "Qual é a sílaba do fim de DADO?", "DO|DA|MA", "DO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: LUA", "SILABAS", "Qual é a sílaba do início de LUA?", "LU|A|MA", "LU", null);
            adicionar(repository, existentes, "Sílabas FIM: LUA", "SILABAS", "Qual é a sílaba do fim de LUA?", "A|LU|MA", "A", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: VACA", "SILABAS", "Qual é a sílaba do início de VACA?", "VA|CA|MA", "VA", null);
            adicionar(repository, existentes, "Sílabas FIM: VACA", "SILABAS", "Qual é a sílaba do fim de VACA?", "CA|VA|MA", "CA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: PIPA", "SILABAS", "Qual é a sílaba do início de PIPA?", "PI|PA|MA", "PI", null);
            adicionar(repository, existentes, "Sílabas FIM: PIPA", "SILABAS", "Qual é a sílaba do fim de PIPA?", "PA|PI|MA", "PA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: CAMA", "SILABAS", "Qual é a sílaba do início de CAMA?", "CA|MA|PA", "CA", null);
            adicionar(repository, existentes, "Sílabas FIM: CAMA", "SILABAS", "Qual é a sílaba do fim de CAMA?", "MA|CA|PA", "MA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: FACA", "SILABAS", "Qual é a sílaba do início de FACA?", "FA|CA|MA", "FA", null);
            adicionar(repository, existentes, "Sílabas FIM: FACA", "SILABAS", "Qual é a sílaba do fim de FACA?", "CA|FA|MA", "CA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: MESA", "SILABAS", "Qual é a sílaba do início de MESA?", "ME|SA|MA", "ME", null);
            adicionar(repository, existentes, "Sílabas FIM: MESA", "SILABAS", "Qual é a sílaba do fim de MESA?", "SA|ME|MA", "SA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: LIVRO", "SILABAS", "Qual é a sílaba do início de LIVRO?", "LI|VRO|MA", "LI", null);
            adicionar(repository, existentes, "Sílabas FIM: LIVRO", "SILABAS", "Qual é a sílaba do fim de LIVRO?", "VRO|LI|MA", "VRO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: MAÇÃ", "SILABAS", "Qual é a sílaba do início de MAÇÃ?", "MA|ÇÃ|PA", "MA", null);
            adicionar(repository, existentes, "Sílabas FIM: MAÇÃ", "SILABAS", "Qual é a sílaba do fim de MAÇÃ?", "ÇÃ|MA|PA", "ÇÃ", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: BANANA", "SILABAS", "Qual é a sílaba do início de BANANA?", "BA|NA|MA", "BA", null);
            adicionar(repository, existentes, "Sílabas FIM: BANANA", "SILABAS", "Qual é a sílaba do fim de BANANA?", "NA|BA|MA", "NA", null);
            adicionar(repository, existentes, "Sílabas MEIO: BANANA", "SILABAS", "Qual é a sílaba do meio de BANANA?", "NA|BA|MA", "NA", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: PEIXE", "SILABAS", "Qual é a sílaba do início de PEIXE?", "PEI|XE|MA", "PEI", null);
            adicionar(repository, existentes, "Sílabas FIM: PEIXE", "SILABAS", "Qual é a sílaba do fim de PEIXE?", "XE|PEI|MA", "XE", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: ÁRVORE", "SILABAS", "Qual é a sílaba do início de ÁRVORE?", "ÁR|VO|RE", "ÁR", null);
            adicionar(repository, existentes, "Sílabas FIM: ÁRVORE", "SILABAS", "Qual é a sílaba do fim de ÁRVORE?", "RE|ÁR|VO", "RE", null);
            adicionar(repository, existentes, "Sílabas MEIO: ÁRVORE", "SILABAS", "Qual é a sílaba do meio de ÁRVORE?", "VO|ÁR|RE", "VO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: CARRO", "SILABAS", "Qual é a sílaba do início de CARRO?", "CAR|RO|MA", "CAR", null);
            adicionar(repository, existentes, "Sílabas FIM: CARRO", "SILABAS", "Qual é a sílaba do fim de CARRO?", "RO|CAR|MA", "RO", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: BONECA", "SILABAS", "Qual é a sílaba do início de BONECA?", "BO|NE|CA", "BO", null);
            adicionar(repository, existentes, "Sílabas FIM: BONECA", "SILABAS", "Qual é a sílaba do fim de BONECA?", "CA|BO|NE", "CA", null);
            adicionar(repository, existentes, "Sílabas MEIO: BONECA", "SILABAS", "Qual é a sílaba do meio de BONECA?", "NE|BO|CA", "NE", null);
            adicionar(repository, existentes, "Sílabas INÍCIO: SAPATO", "SILABAS", "Qual é a sílaba do início de SAPATO?", "SA|PA|TO", "SA", null);
            adicionar(repository, existentes, "Sílabas FIM: SAPATO", "SILABAS", "Qual é a sílaba do fim de SAPATO?", "TO|SA|PA", "TO", null);
            adicionar(repository, existentes, "Sílabas MEIO: SAPATO", "SILABAS", "Qual é a sílaba do meio de SAPATO?", "PA|SA|TO", "PA", null);
            adicionar(repository, existentes, "Imagem e palavra: PANELA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "PANELA|COELHO|RELÓGIO", "PANELA", "/assets/imagens/panela.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: PANELA", "SILABAS", "Qual é a sílaba do início de PANELA?", "PA|NE|LA", "PA", null);
            adicionar(repository, existentes, "Sílabas FIM: PANELA", "SILABAS", "Qual é a sílaba do fim de PANELA?", "LA|PA|NE", "LA", null);
            adicionar(repository, existentes, "Sílabas MEIO: PANELA", "SILABAS", "Qual é a sílaba do meio de PANELA?", "NE|PA|LA", "NE", null);
            adicionar(repository, existentes, "Imagem e palavra: JANELA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "JANELA|ABELHA|CADERNO", "JANELA", "/assets/imagens/janela.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: JANELA", "SILABAS", "Qual é a sílaba do início de JANELA?", "JA|NE|LA", "JA", null);
            adicionar(repository, existentes, "Sílabas FIM: JANELA", "SILABAS", "Qual é a sílaba do fim de JANELA?", "LA|JA|NE", "LA", null);
            adicionar(repository, existentes, "Sílabas MEIO: JANELA", "SILABAS", "Qual é a sílaba do meio de JANELA?", "NE|JA|LA", "NE", null);
            adicionar(repository, existentes, "Imagem e palavra: CANETA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CANETA|BORBOLETA|CADEIRA", "CANETA", "/assets/imagens/caneta.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: CANETA", "SILABAS", "Qual é a sílaba do início de CANETA?", "CA|NE|TA", "CA", null);
            adicionar(repository, existentes, "Sílabas FIM: CANETA", "SILABAS", "Qual é a sílaba do fim de CANETA?", "TA|CA|NE", "TA", null);
            adicionar(repository, existentes, "Sílabas MEIO: CANETA", "SILABAS", "Qual é a sílaba do meio de CANETA?", "NE|CA|TA", "NE", null);
            adicionar(repository, existentes, "Imagem e palavra: TOMATE", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "TOMATE|ESCOVA|CHAVE", "TOMATE", "/assets/imagens/tomate.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: TOMATE", "SILABAS", "Qual é a sílaba do início de TOMATE?", "TO|MA|TE", "TO", null);
            adicionar(repository, existentes, "Sílabas FIM: TOMATE", "SILABAS", "Qual é a sílaba do fim de TOMATE?", "TE|TO|MA", "TE", null);
            adicionar(repository, existentes, "Sílabas MEIO: TOMATE", "SILABAS", "Qual é a sílaba do meio de TOMATE?", "MA|TO|TE", "MA", null);
            adicionar(repository, existentes, "Imagem e palavra: BATATA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "BATATA|MOCHILA|PENTE", "BATATA", "/assets/imagens/batata.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: BATATA", "SILABAS", "Qual é a sílaba do início de BATATA?", "BA|TA|MA", "BA", null);
            adicionar(repository, existentes, "Sílabas FIM: BATATA", "SILABAS", "Qual é a sílaba do fim de BATATA?", "TA|BA|MA", "TA", null);
            adicionar(repository, existentes, "Sílabas MEIO: BATATA", "SILABAS", "Qual é a sílaba do meio de BATATA?", "TA|BA|MA", "TA", null);
            adicionar(repository, existentes, "Imagem e palavra: CAVALO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CAVALO|CAMISA|TIGELA", "CAVALO", "/assets/imagens/cavalo.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: CAVALO", "SILABAS", "Qual é a sílaba do início de CAVALO?", "CA|VA|LO", "CA", null);
            adicionar(repository, existentes, "Sílabas FIM: CAVALO", "SILABAS", "Qual é a sílaba do fim de CAVALO?", "LO|CA|VA", "LO", null);
            adicionar(repository, existentes, "Sílabas MEIO: CAVALO", "SILABAS", "Qual é a sílaba do meio de CAVALO?", "VA|CA|LO", "VA", null);
            adicionar(repository, existentes, "Imagem e palavra: GALINHA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "GALINHA|PIRULITO|LARANJA", "GALINHA", "/assets/imagens/galinha.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: GALINHA", "SILABAS", "Qual é a sílaba do início de GALINHA?", "GA|LI|NHA", "GA", null);
            adicionar(repository, existentes, "Sílabas FIM: GALINHA", "SILABAS", "Qual é a sílaba do fim de GALINHA?", "NHA|GA|LI", "NHA", null);
            adicionar(repository, existentes, "Sílabas MEIO: GALINHA", "SILABAS", "Qual é a sílaba do meio de GALINHA?", "LI|GA|NHA", "LI", null);
            adicionar(repository, existentes, "Imagem e palavra: COELHO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "COELHO|SORVETE|MORANGO", "COELHO", "/assets/imagens/coelho.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: COELHO", "SILABAS", "Qual é a sílaba do início de COELHO?", "CO|E|LHO", "CO", null);
            adicionar(repository, existentes, "Sílabas FIM: COELHO", "SILABAS", "Qual é a sílaba do fim de COELHO?", "LHO|CO|E", "LHO", null);
            adicionar(repository, existentes, "Sílabas MEIO: COELHO", "SILABAS", "Qual é a sílaba do meio de COELHO?", "E|CO|LHO", "E", null);
            adicionar(repository, existentes, "Imagem e palavra: ABELHA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "ABELHA|FOGUETE|UVA", "ABELHA", "/assets/imagens/abelha.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: ABELHA", "SILABAS", "Qual é a sílaba do início de ABELHA?", "A|BE|LHA", "A", null);
            adicionar(repository, existentes, "Sílabas FIM: ABELHA", "SILABAS", "Qual é a sílaba do fim de ABELHA?", "LHA|A|BE", "LHA", null);
            adicionar(repository, existentes, "Sílabas MEIO: ABELHA", "SILABAS", "Qual é a sílaba do meio de ABELHA?", "BE|A|LHA", "BE", null);
            adicionar(repository, existentes, "Imagem e palavra: BORBOLETA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "BORBOLETA|BICICLETA|BOLA", "BORBOLETA", "/assets/imagens/borboleta.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: BORBOLETA", "SILABAS", "Qual é a sílaba do início de BORBOLETA?", "BOR|BO|LE", "BOR", null);
            adicionar(repository, existentes, "Sílabas FIM: BORBOLETA", "SILABAS", "Qual é a sílaba do fim de BORBOLETA?", "TA|BOR|BO", "TA", null);
            adicionar(repository, existentes, "Sílabas MEIO: BORBOLETA", "SILABAS", "Qual é a sílaba do meio de BORBOLETA?", "LE|BOR|BO", "LE", null);
            adicionar(repository, existentes, "Imagem e palavra: ESCOVA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "ESCOVA|RELÓGIO|CASA", "ESCOVA", "/assets/imagens/escova.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: ESCOVA", "SILABAS", "Qual é a sílaba do início de ESCOVA?", "ES|CO|VA", "ES", null);
            adicionar(repository, existentes, "Sílabas FIM: ESCOVA", "SILABAS", "Qual é a sílaba do fim de ESCOVA?", "VA|ES|CO", "VA", null);
            adicionar(repository, existentes, "Sílabas MEIO: ESCOVA", "SILABAS", "Qual é a sílaba do meio de ESCOVA?", "CO|ES|VA", "CO", null);
            adicionar(repository, existentes, "Imagem e palavra: MOCHILA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "MOCHILA|CADERNO|GATO", "MOCHILA", "/assets/imagens/mochila.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: MOCHILA", "SILABAS", "Qual é a sílaba do início de MOCHILA?", "MO|CHI|LA", "MO", null);
            adicionar(repository, existentes, "Sílabas FIM: MOCHILA", "SILABAS", "Qual é a sílaba do fim de MOCHILA?", "LA|MO|CHI", "LA", null);
            adicionar(repository, existentes, "Sílabas MEIO: MOCHILA", "SILABAS", "Qual é a sílaba do meio de MOCHILA?", "CHI|MO|LA", "CHI", null);
            adicionar(repository, existentes, "Imagem e palavra: CAMISA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CAMISA|CADEIRA|PATO", "CAMISA", "/assets/imagens/camisa.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: CAMISA", "SILABAS", "Qual é a sílaba do início de CAMISA?", "CA|MI|SA", "CA", null);
            adicionar(repository, existentes, "Sílabas FIM: CAMISA", "SILABAS", "Qual é a sílaba do fim de CAMISA?", "SA|CA|MI", "SA", null);
            adicionar(repository, existentes, "Sílabas MEIO: CAMISA", "SILABAS", "Qual é a sílaba do meio de CAMISA?", "MI|CA|SA", "MI", null);
            adicionar(repository, existentes, "Imagem e palavra: PIRULITO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "PIRULITO|CHAVE|SAPO", "PIRULITO", "/assets/imagens/pirulito.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: PIRULITO", "SILABAS", "Qual é a sílaba do início de PIRULITO?", "PI|RU|LI", "PI", null);
            adicionar(repository, existentes, "Sílabas FIM: PIRULITO", "SILABAS", "Qual é a sílaba do fim de PIRULITO?", "TO|PI|RU", "TO", null);
            adicionar(repository, existentes, "Sílabas MEIO: PIRULITO", "SILABAS", "Qual é a sílaba do meio de PIRULITO?", "LI|PI|RU", "LI", null);
            adicionar(repository, existentes, "Imagem e palavra: SORVETE", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "SORVETE|PENTE|MALA", "SORVETE", "/assets/imagens/sorvete.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: SORVETE", "SILABAS", "Qual é a sílaba do início de SORVETE?", "SOR|VE|TE", "SOR", null);
            adicionar(repository, existentes, "Sílabas FIM: SORVETE", "SILABAS", "Qual é a sílaba do fim de SORVETE?", "TE|SOR|VE", "TE", null);
            adicionar(repository, existentes, "Sílabas MEIO: SORVETE", "SILABAS", "Qual é a sílaba do meio de SORVETE?", "VE|SOR|TE", "VE", null);
            adicionar(repository, existentes, "Imagem e palavra: FOGUETE", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "FOGUETE|TIGELA|BOLO", "FOGUETE", "/assets/imagens/foguete.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: FOGUETE", "SILABAS", "Qual é a sílaba do início de FOGUETE?", "FO|GUE|TE", "FO", null);
            adicionar(repository, existentes, "Sílabas FIM: FOGUETE", "SILABAS", "Qual é a sílaba do fim de FOGUETE?", "TE|FO|GUE", "TE", null);
            adicionar(repository, existentes, "Sílabas MEIO: FOGUETE", "SILABAS", "Qual é a sílaba do meio de FOGUETE?", "GUE|FO|TE", "GUE", null);
            adicionar(repository, existentes, "Imagem e palavra: BICICLETA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "BICICLETA|LARANJA|DADO", "BICICLETA", "/assets/imagens/bicicleta.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: BICICLETA", "SILABAS", "Qual é a sílaba do início de BICICLETA?", "BI|CI|CLE", "BI", null);
            adicionar(repository, existentes, "Sílabas FIM: BICICLETA", "SILABAS", "Qual é a sílaba do fim de BICICLETA?", "TA|BI|CI", "TA", null);
            adicionar(repository, existentes, "Sílabas MEIO: BICICLETA", "SILABAS", "Qual é a sílaba do meio de BICICLETA?", "CLE|BI|CI", "CLE", null);
            adicionar(repository, existentes, "Imagem e palavra: RELÓGIO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "RELÓGIO|MORANGO|LUA", "RELÓGIO", "/assets/imagens/relogio.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: RELÓGIO", "SILABAS", "Qual é a sílaba do início de RELÓGIO?", "RE|LÓ|GIO", "RE", null);
            adicionar(repository, existentes, "Sílabas FIM: RELÓGIO", "SILABAS", "Qual é a sílaba do fim de RELÓGIO?", "GIO|RE|LÓ", "GIO", null);
            adicionar(repository, existentes, "Sílabas MEIO: RELÓGIO", "SILABAS", "Qual é a sílaba do meio de RELÓGIO?", "LÓ|RE|GIO", "LÓ", null);
            adicionar(repository, existentes, "Imagem e palavra: CADERNO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CADERNO|UVA|SOL", "CADERNO", "/assets/imagens/caderno.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: CADERNO", "SILABAS", "Qual é a sílaba do início de CADERNO?", "CA|DER|NO", "CA", null);
            adicionar(repository, existentes, "Sílabas FIM: CADERNO", "SILABAS", "Qual é a sílaba do fim de CADERNO?", "NO|CA|DER", "NO", null);
            adicionar(repository, existentes, "Sílabas MEIO: CADERNO", "SILABAS", "Qual é a sílaba do meio de CADERNO?", "DER|CA|NO", "DER", null);
            adicionar(repository, existentes, "Imagem e palavra: CADEIRA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CADEIRA|BOLA|VACA", "CADEIRA", "/assets/imagens/cadeira.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: CADEIRA", "SILABAS", "Qual é a sílaba do início de CADEIRA?", "CA|DEI|RA", "CA", null);
            adicionar(repository, existentes, "Sílabas FIM: CADEIRA", "SILABAS", "Qual é a sílaba do fim de CADEIRA?", "RA|CA|DEI", "RA", null);
            adicionar(repository, existentes, "Sílabas MEIO: CADEIRA", "SILABAS", "Qual é a sílaba do meio de CADEIRA?", "DEI|CA|RA", "DEI", null);
            adicionar(repository, existentes, "Imagem e palavra: CHAVE", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "CHAVE|CASA|PIPA", "CHAVE", "/assets/imagens/chave.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: CHAVE", "SILABAS", "Qual é a sílaba do início de CHAVE?", "CHA|VE|MA", "CHA", null);
            adicionar(repository, existentes, "Sílabas FIM: CHAVE", "SILABAS", "Qual é a sílaba do fim de CHAVE?", "VE|CHA|MA", "VE", null);
            adicionar(repository, existentes, "Imagem e palavra: PENTE", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "PENTE|GATO|CAMA", "PENTE", "/assets/imagens/pente.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: PENTE", "SILABAS", "Qual é a sílaba do início de PENTE?", "PEN|TE|MA", "PEN", null);
            adicionar(repository, existentes, "Sílabas FIM: PENTE", "SILABAS", "Qual é a sílaba do fim de PENTE?", "TE|PEN|MA", "TE", null);
            adicionar(repository, existentes, "Imagem e palavra: TIGELA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "TIGELA|PATO|FACA", "TIGELA", "/assets/imagens/tigela.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: TIGELA", "SILABAS", "Qual é a sílaba do início de TIGELA?", "TI|GE|LA", "TI", null);
            adicionar(repository, existentes, "Sílabas FIM: TIGELA", "SILABAS", "Qual é a sílaba do fim de TIGELA?", "LA|TI|GE", "LA", null);
            adicionar(repository, existentes, "Sílabas MEIO: TIGELA", "SILABAS", "Qual é a sílaba do meio de TIGELA?", "GE|TI|LA", "GE", null);
            adicionar(repository, existentes, "Imagem e palavra: LARANJA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "LARANJA|SAPO|MESA", "LARANJA", "/assets/imagens/laranja.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: LARANJA", "SILABAS", "Qual é a sílaba do início de LARANJA?", "LA|RAN|JA", "LA", null);
            adicionar(repository, existentes, "Sílabas FIM: LARANJA", "SILABAS", "Qual é a sílaba do fim de LARANJA?", "JA|LA|RAN", "JA", null);
            adicionar(repository, existentes, "Sílabas MEIO: LARANJA", "SILABAS", "Qual é a sílaba do meio de LARANJA?", "RAN|LA|JA", "RAN", null);
            adicionar(repository, existentes, "Imagem e palavra: MORANGO", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "MORANGO|MALA|LIVRO", "MORANGO", "/assets/imagens/morango.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: MORANGO", "SILABAS", "Qual é a sílaba do início de MORANGO?", "MO|RAN|GO", "MO", null);
            adicionar(repository, existentes, "Sílabas FIM: MORANGO", "SILABAS", "Qual é a sílaba do fim de MORANGO?", "GO|MO|RAN", "GO", null);
            adicionar(repository, existentes, "Sílabas MEIO: MORANGO", "SILABAS", "Qual é a sílaba do meio de MORANGO?", "RAN|MO|GO", "RAN", null);
            adicionar(repository, existentes, "Imagem e palavra: UVA", "IMAGEM_PALAVRA", "Qual palavra corresponde à imagem?", "UVA|BOLO|MAÇÃ", "UVA", "/assets/imagens/uva.svg");
            adicionar(repository, existentes, "Sílabas INÍCIO: UVA", "SILABAS", "Qual é a sílaba do início de UVA?", "U|VA|MA", "U", null);
            adicionar(repository, existentes, "Sílabas FIM: UVA", "SILABAS", "Qual é a sílaba do fim de UVA?", "VA|U|MA", "VA", null);
        };
    }

    private void adicionar(
            AtividadeRepository repository,
            Set<String> existentes,
            String titulo,
            String tipo,
            String enunciado,
            String opcoes,
            String correta,
            String imagemUrl
    ) {
        if (!existentes.add(titulo)) {
            return;
        }

        Atividade atividade = new Atividade();
        atividade.setTitulo(titulo);
        atividade.setTipo(tipo);
        atividade.setEnunciado(enunciado);
        atividade.setOpcoes(opcoes);
        atividade.setRespostaCorreta(correta);
        atividade.setImagemUrl(imagemUrl);
        atividade.setPontos(10);
        repository.save(atividade);
    }
}
