# Aula 04 — Introdução a Banco de Dados na Perspectiva de System Design

> Origem: análise do áudio pelo Gemini, com normalização textual. Não é transcrição literal; exemplos visuais e código apresentados só na tela exigem conferência no vídeo.

Resumo Detalhado: Bancos de Dados em System Design

Esta aula aborda a perspectiva fundamental dos bancos de dados dentro do planejamento de arquitetura de software (System Design), destacando que a principal finalidade de um banco de dados é garantir a retenção de informações e o conhecimento ao longo da história da humanidade.

### 1. Evolução Histórica e Filosofia

A necessidade de registrar dados evoluiu desde as pinturas rupestres em cavernas (pouco seguras e eficientes) até papiros, livros, bibliotecas e universidades medievais (muitas faculdades clássicas como Oxford e Cambridge nasceram de estruturas eclesiásticas). Com o volume de dados crescendo exponencialmente, a humanidade migrou para o armazenamento digital, passando de monólitos centralizados para sistemas distribuídos.

### 2. Aplicações Práticas e Oportunidades

Em arquiteturas stateless, a instância de aplicação não precisa reter estado local entre requisições. Dados podem ser externalizados para bancos, caches e outros mecanismos. O instrutor compartilha um caso real em um grande banco brasileiro onde, ao analisar os dados de consumo de clientes de cartões Black, descobriu-se um alto volume de compras de vinhos. Isso levou à criação de uma plataforma de e-commerce de vinhos que se tornou uma das maiores da América Latina por um breve período, demonstrando como a análise de dados gera novas oportunidades de negócio.

### 3. Tipos de Bancos de Dados e Paradigmas

Bancos de Dados Relacionais (SQL): Surgidos na década de 1970 com forte embasamento em normalização. São maduros, altamente eficientes e escalam verticalmente (aumentando o poder computacional da máquina). Garantem forte consistência.

Bancos de Dados Não Relacionais (NoSQL): Focam primariamente na escalabilidade horizontal (distribuição em múltiplos nós). Não possuem um padrão universal único como o SQL, o que exige maior carga cognitiva dos desenvolvedores. Dividem-se nas seguintes categorias principais:

Chave-Valor (Key-Value): Estruturas semelhantes a mapas ou dicionários (ex: Redis, Memcached, Apache Ignite). Ideais para sessões de usuário, cache de segundo nível e contadores.

Colunar (Wide Column): Evolução do chave-valor organizada em famílias de colunas (ex: Cassandra). Muito utilizados em séries temporais (time series), sensores industriais e análises em tempo real.

Documento (Document): Armazenam dados em formatos JSON ou XML (ex: MongoDB). Permitem buscas flexíveis por campos arbitrários e suporte a subdocumentos.

Grafo (Graph): Focados em relacionamentos complexos, direcionamentos e propriedades bidirecionais (ex: Neo4j, Amazon Neptune). Utilizados em motores de recomendação e detecção de fraudes.

NewSQL e Multivalue: Soluções híbridas que trazem recursos relacionais a bancos NoSQL (ou vice-versa, como o PostgreSQL suportando JSON) e bancos multivalue (como o Oracle) que suportam múltiplos modelos de dados simultaneamente.

### 4. Teorema de CAP e Trade-offs

O Teorema de CAP dita que um sistema distribuído pode garantir no máximo duas das três propriedades simultaneamente: Consistência (Consistency), Disponibilidade (Availability) e Tolerância a Partição (Partition Tolerance). Bancos relacionais tradicionais priorizam consistência, enquanto muitos NoSQL sacrificam parte da consistência em prol da disponibilidade e tolerância a falhas.

### 5. Estratégias de Arquitetura e Processamento

Sharding (Particionamento): Divisão de dados em partições menores distribuídas para ganho de escala horizontal, embora traga grande complexidade para rotinas de backup.

Replicação Master-Slave vs. Masterless: Modelos de distribuição de carga entre nós de leitura e escrita, ponderando consistência eventual versus imediata.

ETL vs. ELT e Data Warehouses vs. Data Lakes:

Data Warehouse: Estruturado, relacional, voltado a relatórios corporativos e analistas de negócios.

Data Lake: Armazenamento bruto (raw data), flexível e de baixo custo (ex: Amazon S3), ideal para cientistas de dados.

ETL: Transforma os dados antes de carregá-los.

ELT: Carrega os dados brutos e realiza a transformação posteriormente.

Limitações Mencionadas

A curva de aprendizado e a carga cognitiva de bancos NoSQL são elevadas, pois diferem do modelo relacional ensinado tradicionalmente nas faculdades.

Desafios operacionais complexos em rotinas de backup e restauração de dados distribuídos via sharding.

Perguntas de Revisão (Banco de Dados)

Qual é o objetivo principal da criação e evolução dos bancos de dados ao longo da história?

Resposta: Garantir a retenção de informações e o conhecimento humano ao longo do tempo de forma segura e eficiente.

Qual é a principal diferença de escalabilidade entre bancos relacionais e não relacionais?

Resposta: Bancos relacionais escalam primordialmente de forma vertical (aumentando os recursos da máquina), enquanto os não relacionais focam na escalabilidade horizontal (distribuição em múltiplos nós).

Em quais cenários o modelo de banco de dados do tipo Grafo é mais recomendado?

Resposta: Em cenários que exigem mapeamento profundo de relacionamentos e propriedades entre entidades, como motores de recomendação e detecção de fraudes.

O que estabelece o Teorema de CAP em sistemas distribuídos?

Resposta: Que um sistema distribuído pode garantir simultaneamente no máximo duas entre três propriedades: Consistência, Disponibilidade e Tolerância a Partição.

Qual é a principal distinção operacional entre um Data Warehouse e um Data Lake?

Resposta: O Data Warehouse armazena dados estruturados e refinados para análises de negócios, enquanto o Data Lake armazena dados brutos (raw data) em seu formato original, priorizando flexibilidade e baixo custo.

## Ressalvas de estudo

Leia a [revisão técnica dos resumos](../revisao-tecnica.md) antes de usar estas notas como referência. Estatísticas e generalizações da análise automática não devem ser tomadas como fatos verificados.
