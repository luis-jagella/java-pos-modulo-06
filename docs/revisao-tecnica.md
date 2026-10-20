# Revisão técnica dos resumos automáticos

As notas baseadas em áudio foram produzidas com apoio do Gemini. São material de estudo, não uma transcrição nem garantia de que cada frase foi dita pelo professor. Os pontos abaixo corrigem simplificações e ajudam a revisar os conceitos.

## System Design e documentação

- Camadas lógicas organizam responsabilidades; tiers descrevem distribuição de execução. Um monólito pode ter boa modularização interna. Separar máquinas não garante tolerância a falhas.
- Escala horizontal tem limites de coordenação, consistência, custo e capacidade dos componentes compartilhados; não é infinita.
- O Manifesto Ágil é de 2001, não de aproximadamente 2010.
- No C4, componentes ficam dentro de containers; nem todo componente é implantável independentemente. Container não significa necessariamente Docker. Não existe obrigação de um único diagrama por nível. Veja o [C4 Model](https://c4model.com/).
- Percentuais sobre adoção de ADRs, produtividade ou sucesso de projetos presentes no resumo não foram rastreados às pesquisas originais. Não os use como evidência quantitativa. Preferimos explicar contexto, decisão e consequências.

## Dados e comunicação

- Stateless significa que a instância não depende de estado local entre requisições. O estado pode estar em banco, cache, armazenamento externo ou tokens; não obrigatoriamente em um banco relacional.
- SQL e NoSQL não determinam sozinhos escala horizontal nem modelo de consistência. Essas propriedades dependem do produto, topologia e configuração. “Multimodelo” não é sinônimo de “multivalue”.
- CAP não significa escolher arbitrariamente duas letras. **Durante uma partição de rede**, há conflito entre consistência forte e disponibilidade conforme as definições do teorema. Veja a [revisão do próprio Eric Brewer](https://www.infoq.com/articles/cap-twelve-years-later-how-the-rules-have-changed/).
- REST é um estilo arquitetural e não está preso a HTTP/1.1 ou JSON. gRPC não é automaticamente mais rápido em qualquer carga.
- Síncrono não é sinônimo obrigatório de thread bloqueada. Uma chamada HTTP pode usar I/O não bloqueante. Mensageria também tem falhas, duplicação e requisitos de entrega/idempotência.

## Código, CDI e DDD

- Ter `if` não é automaticamente violar SOLID. Strategy e qualificadores são úteis quando há variação real de comportamento, não para remover todo condicional.
- Em CDI, a anotação é `@ApplicationScoped`. Ela define uma instância contextual por aplicação, não um Singleton global da JVM nem segurança automática entre threads. `@Singleton` é um pseudoescopo distinto. Veja a [especificação CDI](https://jakarta.ee/specifications/cdi/4.1/jakarta-cdi-spec-4.1.html).
- Uma visão não modificável de uma lista não é necessariamente uma cópia imutável. Para um snapshot defensivo, considere `List.copyOf`, respeitando também a mutabilidade dos elementos.
- DDD não tem uma divisão quantitativa universal de 80/20. A mensagem relevante é não ignorar o design estratégico.
- jMolecules e ArchUnit verificam as regras configuradas, não impedem toda violação possível nem garantem correção do negócio. Veja o [projeto oficial](https://github.com/xmolecules/jmolecules).

Essas observações são complementos de revisão e não novas falas atribuídas à aula.
