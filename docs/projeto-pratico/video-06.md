# Projeto — Vídeo 06: Imutabilidade e Builder

> Tarefa 06. Fonte: áudio, analisado pelo Gemini. Notas de estudo, não transcrição literal nem reprodução do código visual.

Resumo e Notas Detalhadas da Aula: Imutabilidade e Refatoração de Código em Java
1. Introdução aos Conceitos de Imutabilidade

A aula aborda a evolução na maturidade do desenvolvimento de software em Java, questionando o hábito comum de criar classes com getters e setters irrestritos logo de início. O objetivo central é minimizar a mutabilidade, aplicando o conceito de arquitetura negativa (inspirado nos trabalhos de Michael Feathers sobre código legado), que dita regras sobre o que não pode ser feito no código para garantir maior previsibilidade e organização.

Encapsulamento vs. Imutabilidade: Enquanto o encapsulamento tradicional protege o estado interno exigindo métodos de acesso, a imutabilidade garante que, uma vez criado o objeto, seu estado jamais poderá ser alterado externamente.

Vantagens: Reduz efeitos colaterais indesejados, facilita testes unitários, elimina condições de corrida em ambientes concorrentes e torna o código mais fácil de raciocinar.

2. Refatoração do Repositório de Markdowns (Cotubas)

O projeto prático inicia com a refatoração de um componente responsável por gerenciar diretórios e arquivos Markdown (e-books/capítulos).

Problema Inicial: A implementação criava objetos de capítulos de forma parcial ou permitia mutações em listas e atributos internos.

Evolução da Abstração:

Análise da assinatura dos métodos para retornar listas imutáveis de arquivos Markdown ou conteúdos processados.

Substituição de estruturas mutáveis tradicionais por coleções imutáveis. O instrutor discute a diferença crucial entre Collectors.toList() (que em versões clássicas gerava listas mutáveis) e toList() ou Stream.toList() / Stream.map().toList(), que retornam coleções imutáveis (unmodifiable lists).

Uso de unmodifiable nas coleções internas para assegurar que nenhuma alteração externa ocorra na lista de capítulos ou conteúdos.

3. Aplicação de Imutabilidade em Coleções e Records

A discussão aprofunda-se no uso de recursos modernos do Java (versões recentes) para imutabilidade estrita:

Records: Geram acessores, equals, hashCode e toString. Campos finais não tornam automaticamente imutáveis os objetos referenciados.

Cuidados com Records e Construtores: Quando atributos precisam ser validados ou copiados defensivamente (como listas internas), recorre-se a construtores compactos ou canônicos.

Coleções Imutáveis: Destaca-se a importância de garantir que qualquer coleção passada como parâmetro ou retornada por um método seja uma cópia defensiva ou uma lista imutável (unmodifiable), impedindo que o chamador modifique o estado interno do objeto.

4. Ferramentas, Padrões de Projeto e Limitações

Design Patterns: Avaliação do padrão Builder para a construção de objetos complexos em etapas. Discute-se o uso do Lombok (com suas anotações como @Builder ou @Value) versus bibliotecas especializadas como RecordBuilder (ex: versão 52).

Annotation Processors: O uso de processadores de anotações no Maven para gerar código em tempo de compilação (geração de builders para records).

Limitações e Desafios:

O áudio aponta limitações visuais evidentes: trechos de código manipulados na tela da IDE não são descritos verbalmente em sua totalidade, exigindo que o aluno acompanhe a lógica conceitual de refatoração passo a passo.

Erros de compilação temporários durante a refatoração incremental (ajustes em assinaturas de métodos, remoção de setters e adaptação de construtores).

Perguntas de Revisão (com Respostas)

O que é arquitetura negativa no contexto da imutabilidade discutida na aula?

Resposta: É uma abordagem de design (inspirada em Michael Feathers) onde o desenvolvedor define restrições claras sobre o que não pode ser feito no código (por exemplo, impedir a alteração de estados após a criação do objeto ou proibir a extensão de classes), garantindo maior segurança e previsibilidade.

Qual é a principal diferença entre utilizar Collectors.toList() tradicional e os métodos modernos de coleta como toList() em fluxos (Streams)?

Resposta: `Collectors.toList()` não garante mutabilidade ou implementação específica; `Stream.toList()` retorna uma lista não modificável. Isso não torna os elementos profundamente imutáveis.

Por que o uso excessivo de métodos setters em classes de domínio é desencorajado em estágios avançados de projeto?

Resposta: Porque os setters permitem que o estado do objeto seja modificado a qualquer momento e em qualquer lugar do sistema, o que dificulta o rastreamento de bugs, quebra a previsibilidade e gera efeitos colaterais indesejados.

Como o uso de Records no Java auxilia na implementação de imutabilidade?

Resposta: Records têm campos finais e acessores gerados, reduzindo boilerplate. Imutabilidade profunda depende dos componentes e de cópias defensivas quando necessárias.

O que deve ser evitado ao expor coleções internas em objetos imutáveis?

Resposta: Deve-se evitar retornar diretamente a referência da coleção mutável interna. A boa prática consiste em retornar cópias defensivas ou coleções imutáveis para impedir que códigos externos alterem o estado interno do objeto.

## Acompanhamento da prática

O resumo registra o conteúdo estudado. A implementação e os testes são acompanhados separadamente no [plano do projeto](README.md).
