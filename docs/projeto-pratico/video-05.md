# Projeto — Vídeo 05: OCP e geração HTML

> Tarefa 05. Fonte: legenda automática do player. Notas de estudo, não transcrição literal nem reprodução do código visual.

## OCP aplicado à seleção de geradores

A tarefa parte do serviço que ainda escolhe PDF ou EPUB com condicionais. O objetivo é permitir uma nova implementação sem alterar a orquestração. Não se trata de proibir todo `if`, mas de isolar um eixo real de variação: o formato do e-book.

## Qualificador e seleção dinâmica no CDI

Cria-se uma anotação qualificada que recebe o formato do e-book como valor. Sua retenção precisa estar disponível em runtime, pois o contêiner usa essa informação para selecionar uma implementação. Os alvos da anotação devem permitir aplicá-la nos tipos e nos pontos de uso apropriados.

Cada gerador declara qual formato atende. Uma classe que estende `AnnotationLiteral` e implementa a anotação permite construir o qualificador programaticamente, recebendo o enum de formato. A aula segue o exemplo do filtro de pagamentos apresentado na teoria.

O serviço injeta `@Any Instance<GeradorEbook>` e usa `select` com o literal construído para obter o gerador adequado. Assim deixa de possuir campos e ramificações específicos para PDF e EPUB. A configuração deve produzir exatamente uma implementação para cada formato; ausência ou ambiguidade é um erro de composição que precisa ser tratado.

## Prova de extensão: site HTML

O novo gerador implementa o contrato comum e é qualificado para HTML. Além da implementação, o enum de formatos precisa reconhecer a nova opção. O núcleo de orquestração não precisa conhecer a classe concreta.

A saída HTML é um diretório, diferente do arquivo único dos formatos anteriores. Para cada capítulo é escrito um documento com charset UTF-8, título e corpo. Um prefixo numérico mantém a ordem; o nome derivado do título é normalizado. O índice apresenta título do livro, autor e sumário com links para os capítulos.

É usado um mapa que mantém a ordem de inserção para associar capítulos e arquivos. A construção do sumário transforma essas entradas em itens de lista. Durante o teste, os links inicialmente incluem o diretório de saída duas vezes. A correção usa somente o nome do arquivo no `href`, preservando referências relativas ao `index.html`.

O fluxo é verificado pela CLI e pela abertura do índice no navegador. A demonstração compara o diff: uma implementação e o reconhecimento do novo formato, sem reescrever o serviço. A próxima tarefa trata da imutabilidade, pois o domínio ainda possui mutações que podem ser reduzidas.

## Cuidados adicionais da nossa prática

O diretório de saída não deve ser apagado recursivamente se já existir. Títulos e metadados inseridos no HTML precisam de escape; normalização de nomes deve evitar colisões. Esses são cuidados da implementação do repositório, não uma afirmação de que foram todos demonstrados na aula.

## Revisão

1. **O que fica fechado para modificação?** O fluxo orquestrador diante da adição de um novo gerador.
2. **Para que serve AnnotationLiteral?** Construir uma instância do qualificador para seleção programática.
3. **Por que usar `@Any`?** Para selecionar entre todas as implementações qualificadas do contrato.
4. **Qual a diferença da saída HTML?** É um diretório com índice e um documento por capítulo.
5. **Como foi corrigido o link quebrado do índice?** Usando o nome do arquivo de capítulo, não o caminho com o diretório repetido.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
