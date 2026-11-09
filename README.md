📚 AlfabetizaWeb
Plataforma web de apoio à alfabetização infantil, com atividades interativas, gamificação e acompanhamento de progresso.

🌐 Acessar aplicação · 💻 Repositório no GitHub
Projeto acadêmico e de portfólio desenvolvido no contexto do curso de Engenharia de Software e das Atividades Extensionistas da UNINTER.

Sobre o projeto
O AlfabetizaWeb foi criado para oferecer uma experiência lúdica de prática da alfabetização. A aplicação reúne exercícios com letras, sílabas e palavras, registra o desempenho das rodadas e permite que responsáveis acompanhem o progresso de alunos cadastrados.

O projeto evoluiu de uma proposta e modelagem acadêmicas para uma aplicação full stack publicada na internet. Não foram realizados estudos com crianças que comprovem ganhos de aprendizagem; a demonstração disponível evidencia o funcionamento técnico da plataforma.
Funcionalidades
- Cadastro e autenticação de responsáveis.
- Cadastro, seleção e gerenciamento de alunos.
- Atividades interativas de reconhecimento de letras e sílabas.
- Exercícios de associação entre imagens e palavras.
- Feedback das respostas e pontuação por rodada.
- Registro de resultados e visualização do progresso.
- Interface responsiva para computadores e celulares.
Tecnologias
Camada	Tecnologias
Frontend	Angular, TypeScript, HTML e CSS
Backend	Java 21, Spring Boot e Maven
Banco de dados	PostgreSQL
Versionamento	Git e GitHub
Hospedagem	Vercel (frontend), Render (API) e Neon (banco)


Arquitetura
Navegador (Angular)
       |
       | requisições /api
       v
Vercel (frontend e proxy)
       |
       v
Render (API Java / Spring Boot)
       |
       v
Neon (PostgreSQL)
Estrutura do repositório
alfabetizaweb/
├── frontend/     # Aplicação Angular
├── backend/      # API Spring Boot
└── README.md     # Documentação do projeto
Executando localmente
Pré-requisitos: Node.js e npm compatíveis com a versão do Angular do projeto, Java 21, Maven e PostgreSQL.
1. Clone o repositório:
   git clone https://github.com/antoniocarlosh/alfabetizaweb.git
   cd alfabetizaweb
2. Configure um banco PostgreSQL local e as credenciais do backend. A aplicação utiliza as variáveis de ambiente DATABASE_URL, DATABASE_USERNAME e DATABASE_PASSWORD no ambiente de produção; confira a configuração em backend/src/main/resources/ para ajustar a execução local. Não publique senhas no repositório.
3. Inicie a API:
   cd backend
   ./mvnw spring-boot:run
4. Em outro terminal, inicie o frontend:
   cd frontend
   npm install
   npm start
5. Acesse o endereço local indicado pelo Angular (normalmente http://localhost:4200). Verifique a configuração do proxy /api para que o frontend alcance a API local.
Nota: as instruções de configuração local devem ser conferidas com os scripts e arquivos de ambiente da versão atual do repositório. O frontend em produção utiliza o caminho relativo /api.

Demonstração e capturas de tela
Acesse a versão publicada: https://alfabetizaweb.vercel.app/

Observação: por utilizar hospedagem gratuita, a API pode levar alguns instantes para responder no primeiro acesso após um período de inatividade.
Próximas melhorias
- Ampliar o catálogo de atividades e níveis de dificuldade.
- Aprimorar a acessibilidade e os recursos multimídia.
- Evoluir os relatórios de acompanhamento.
Antônio Carlos Honorato Nunes
Estudante de Engenharia de Software · Projeto acadêmico e de portfólio
AlfabetizaWeb — aprender pode ser uma aventura.
