# Projeto — Vídeo 04: DIP e injeção com CDI/Weld

> Tarefa 04. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

## DIP: proteger políticas de negócio dos detalhes técnicos

A aula começa revisando a definição de Dependency Inversion: módulos de alto nível e módulos de baixo nível devem depender de abstrações; detalhes devem depender de abstrações, e não o contrário. O objetivo não é criar interfaces para todas as classes, mas proteger regras mais estáveis das mudanças de tecnologia.

No Cotuba, Ebook, Capítulo e Formato representam o domínio. O serviço orquestrador pertence à aplicação. Leitura de arquivos, CommonMark e geração de PDF/EPUB são detalhes de infraestrutura. O DTO de parâmetros pertence à aplicação, não necessariamente à CLI só porque nasce do terminal.

## Identificação e inversão das dependências

O diagrama de classes destaca as dependências problemáticas: o serviço usa diretamente leitores de propriedades, renderizadores e geradores concretos. São extraídas interfaces com nomes que descrevem contratos; implementações podem revelar a tecnologia, como leitura em arquivo e renderização com CommonMark. Os dois geradores passam a compartilhar uma interface com uma operação de geração que recebe o agregado Ebook.

Declarar uma variável pela interface não basta se o serviço continua criando implementações com `new`. A instanciação precisa ir para a composição da aplicação, uma fábrica ou um contêiner de injeção. É a distinção entre inverter dependências de código e apenas trocar nomes.

## CDI/Weld

A resolução utiliza Weld, implementação de CDI, para montar o grafo. As implementações são gerenciadas por escopo de aplicação; o serviço recebe suas dependências por construtor com `@Inject`. Na entrada da aplicação, um contêiner é inicializado e fornece os objetos necessários. O encerramento do contêiner precisa acompanhar o fim da execução.

Há duas implementações do contrato de geração, então a injeção sem qualificação fica ambígua. Nesta etapa são usados nomes/qualificadores para distinguir PDF e EPUB. A aula seguinte evolui essa seleção para evitar que o serviço enumere manualmente cada implementação.

O empacotamento deve preservar os metadados de descoberta e os serviços das dependências. Compilar os testes não é suficiente: também se executa o JAR para verificar o bootstrap real do CDI. O ciclo de refatoração preserva a geração dos dois formatos.

## Correção de codificação e limites

É corrigida a leitura de metadados com acentos usando um Reader com UTF-8 em vez de depender da interpretação padrão de bytes pelo carregador de propriedades. A legenda contém erros fonéticos nos nomes das APIs e não permite copiar fielmente os trechos visuais. As assinaturas e a configuração final devem ser verificadas por compilação e testes.

Escopo de aplicação não torna as classes automaticamente thread-safe. DIP e injeção de dependências são relacionados, mas distintos: é possível aplicar DIP com composição manual, sem framework.

## Revisão

1. **Qual dependência o DIP combate?** A política de alto nível depender diretamente do detalhe de baixo nível.
2. **Criar interfaces resolve sozinho?** Não, se a regra continua instanciando as classes concretas.
3. **Qual o papel do Weld?** Montar e gerenciar os objetos e suas dependências usando CDI.
4. **Por que qualificar os geradores?** Há mais de uma implementação elegível do mesmo contrato.
5. **Por que testar o JAR além dos testes de classes?** Descoberta de beans, serviços e recursos pode mudar com o empacotamento.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
