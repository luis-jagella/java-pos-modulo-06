# Podcast 02 — Fazendo as pazes com o legado — ferramentas e prática

> Fonte: legenda automática do player, resumida pelo Gemini. Não é transcrição literal. Título descritivo para estudo; o portal usa POSCAST 02. Nomes dos participantes não foram confirmados com segurança.

Tema

Aplicação prática de ferramentas para refatoração e migração de sistemas legados em Java.

Exemplos Práticos

Projeto monolítico de entrega de comida (semelhante a um sistema de cardápios, restaurantes, cozinhas, pedidos, pagamentos e avaliações), construído inicialmente em Java 8 com Spring Boot 2.

Demonstração de uso do Sonar (SonarQube Cloud e SonarQube for IDE) para análise de métricas, bugs, code smells e vulnerabilidades.

Uso do plugin Maven do OpenRewrite para aplicar receitas de migração de versões.

Utilização da ferramenta de linha de comando Gemini CLI para interagir com o código via IA, gerar planos de migração e modificar dependências e arquivos de configuração.

Conceitos Explicados

Legado "Nutella" vs. "Raiz": Classificação informal de sistemas legados. O "Nutella" usa tecnologias um pouco mais modernas (como Spring Boot com Docker, embora em versões desatualizadas como Java 8), enquanto o "Raiz" envolve configurações complexas em XML anteriores ao Spring Boot.

Injeção de Dependência por Construtor vs. @Autowired: O Sonar aponta o uso de injeção de campo (@Autowired) como má prática, recomendando a injeção via construtor com o modificador final.

Uso de DTOs (Data Transfer Objects): Exposição direta de entidades de banco de dados (@Entity) em endpoints de API é tratada como um grave problema de design de código e segurança, devendo ser substituída por DTOs de requisição/resposta.

Ferramentas Determinísticas vs. Estatísticas: Diferenciação entre ferramentas baseadas em regras e receitas fixas (como OpenRewrite e Sonar) e ferramentas baseadas em inteligência artificial (como o Gemini CLI), que exigem revisão rigorosa de código.

Refatorações, Problemas e Correções

Problema de Injeção de Dependência: Uso de @Autowired em controladores. Correção sugerida: substituir por injeção via construtor e tornar os campos final.

Problema de Referências em Métodos: Uso de expressões lambda verbosas ao passar parâmetros para construtores. Correção sugerida: substituir por method references (place with method reference).

Exposição de Entidades em Controladores (@Entity): Recebimento e retorno direto de entidades de banco de dados nos endpoints POST. Correção sugerida: migrar para DTOs.

Variáveis não utilizadas em Endpoints: Identificação de variáveis no path (como restaurantId) que não estavam sendo usadas no escopo da função.

Limpeza de Código: Remoção de imports não utilizados, ajustes em exceções utilizando a anotação @serial e substituição de funções matemáticas aleatórias por ThreadLocalRandom.

Migração de Versões: Atualização do projeto de Java 8 para Java 17/21 e elevação da versão do Spring Boot (de 2.0.8 para versões compatíveis superiores, como 3.2.x), além de ajustes em bibliotecas de segurança (Spring Security) e tokens (JJWT), onde a ordem dos parâmetros (ex: algoritmo e secret) sofreu alterações nas novas versões.

Limitações do que depende da tela

Visualização de dashboards de qualidade do SonarQube Cloud (status de falha, métricas de confiabilidade, manutenibilidade, segurança e gráficos de débito técnico).

Navegação por arquivos de código-fonte e painéis de avisos (warnings) na IDE (IntelliJ).

Acompanhamento de comandos executados no terminal através da interface de linha de comando (CLI) do Gemini.

5 Perguntas com Respostas

Qual era a versão principal do Java e do Spring Boot do projeto legado apresentado no início da demonstração?

O projeto utilizava Java 8 e Spring Boot versão 2.0.8.

Por que o SonarQube aponta o uso da anotação @Autowired em campos de classes controladoras como um problema?

Porque é considerada uma má prática de design; o ideal é realizar a injeção de dependência através do construtor da classe, preferencialmente marcando os atributos como final.

Qual ferramenta baseada em "receitas" foi utilizada para automatizar mudanças estruturais no projeto de build (Maven)?

O OpenRewrite (através do OpenRewrite Maven Plugin).

Qual é o principal alerta destacado pelos apresentadores ao utilizar ferramentas de inteligência artificial gratuitas (como o Gemini CLI) em projetos de software?

O alerta de que, ao utilizar versões gratuitas, o código do projeto pode ser utilizado para treinar os modelos da empresa provedora (como o Google), tornando o uso proibido em códigos proprietários de empresas sem a devida configuração de privacidade (como o uso de APIs pagas ou corporativas).

Quais foram as quatro principais ferramentas citadas e demonstradas para auxiliar no trabalho com código legado?

O SonarQube Cloud/SonarQube for IDE, o OpenRewrite, a ferramenta de IA via terminal (Gemini CLI) e a própria IDE (IntelliJ).
