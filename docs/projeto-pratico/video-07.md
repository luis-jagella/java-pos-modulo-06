# Projeto — Vídeo 07: C4 do Cotubify

> Tarefa 07. Fonte: áudio, analisado pelo Gemini. Notas de estudo, não transcrição literal nem reprodução do código visual.

Resumo e Notas Detalhadas da Aula: Simulação de Entrevista de System Design (Projeto Cotubefy)
1. Contexto e Simulação de Entrevista

A aula simula uma entrevista técnica de System Design para o projeto Cotubefy (uma plataforma de autopublicação e venda de e-books). A dinâmica coloca os participantes em papéis cruzados: um assumindo uma postura rigorosa de recrutador e o outro como o candidato que precisa extrair os requisitos e projetar a arquitetura do sistema.

Objetivo: Demonstrar que em uma entrevista real de System Design, os requisitos raramente vêm mastigados; o candidato precisa fazer perguntas estratégicas para mapear o escopo, os usuários e as integrações necessárias antes de desenhar qualquer solução.

2. Levantamento de Requisitos e Perfis de Usuários

Durante a entrevista simulada, os seguintes perfis e fluxos são definidos:

Perfis de Usuários:

Autor (Escritor Técnico / Desenvolvedor): Escreve livros em formato Markdown divididos em capítulos, gerencia versões em repositórios Git (GitHub/GitLab), acompanha um painel analítico de vendas e solicita saques/transferências de valores acumulados.

Leitor: Navega pelo catálogo da loja online, realiza buscas, efetua compras utilizando meios de pagamento suportados, baixa os e-books gerados nos formatos PDF ou ePub (para leitura em dispositivos como Kindle) e recebe comprovantes e recibos por e-mail.

Sistemas Externos Integrados:

Provedor de código fonte / Repositório Git (para leitura dos Markdowns dos autores).

Gateway de Pagamentos (focado inicialmente em Pix e Cartão de Crédito).

Sistema de E-mail (para envio de recibos, notificações e avisos aos usuários).

3. Modelagem Arquitetural e Ferramentas

Modelo C4: Utilizado para documentação de arquitetura de software em múltiplos níveis (Contexto, Containers, Componentes e Código).

Mermaid: Ferramenta mencionada para criar diagramas baseados em texto diretamente na documentação.

Diagrama de Contexto (Modelo C4 - Nível 1): Mapeamento de alto nível mostrando o sistema principal (Cotubefy), os atores (Autor e Leitor) e os sistemas externos (Git, Gateway de Pagamento e Servidor de E-mail), definindo claramente as fronteiras e responsabilidades de cada componente.

4. Decisões de Design e Limitações

Decisão de Projeto: Evitar o design prematuro ou "futurologia" arquitetural. O projeto foca em atender às necessidades atuais do negócio de forma incremental, mantendo a porta aberta para refatorações futuras caso novos requisitos surjam.

Limitações: A simulação verbal não substitui a visualização interativa de um quadro branco (whiteboard) ou de ferramentas de diagramação, exigindo forte alinhamento verbal entre o entrevistador e o candidato para garantir que todos os fluxos de dados e entidades estejam corretos.

Perguntas de Revisão (com Respostas)

Qual é a importância de realizar perguntas investigativas no início de uma entrevista de System Design (como demonstrado na simulação do Cotubefy)?

Resposta: Sirve para descobrir os requisitos funcionais e não-funcionais reais, os perfis de usuários e as limitações do sistema, evitando que o candidato projete uma solução genérica ou baseada em suposições incorretas.

Quais são os dois principais perfis de usuários identificados na plataforma Cotubefy?

Resposta: O Autor (responsável por escrever e publicar e-books em Markdown, gerenciar repositórios e acompanhar painéis de vendas) e o Leitor (responsável por navegar na loja, comprar e baixar os e-books em formatos como PDF e ePub).

Quais foram os meios de pagamento definidos no escopo inicial do sistema de pagamentos do Cotubefy?

Resposta: Pix e cartão de crédito.

O que é o modelo C4 mencionado na aula e para que ele serve?

Resposta: É um modelo de arquitetura de software hierárquico baseado em quatro níveis (Contexto, Containers, Componentes e Código) que serve para documentar e comunicar a estrutura de um sistema de forma clara e padronizada.

Por que o instrutor desaconselha a prática de "futurologia" arquitetural ao desenhar um sistema pela primeira vez?

Resposta: Porque tentar prever e implementar abstrações complexas para cenários futuros incertos gera trabalho desnecessário, complexidade acidental e código difícil de manter, sendo preferível focar nas necessidades atuais do domínio com abertura para refatorações posteriores.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
