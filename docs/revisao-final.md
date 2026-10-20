# Revisão do módulo 06

Roteiro próprio para consolidar as aulas, não respostas da avaliação. Os resumos vêm de áudio/legenda e têm [ressalvas técnicas](revisao-tecnica.md). Ter notas no Git não significa ter aprendido o conteúdo ou concluído o portal.

## Como retomar

1. **Visão do sistema:** aulas 01–06. Explique as fronteiras, os dados e como os componentes conversam; consulte os C4 e ADRs da prática.
2. **Design do código:** aulas 07–12. Localize domínio, portas, caso de uso e adaptadores no Cotuba. Rode os testes antes de alterar o código.
3. **Mudança pequena:** adicione uma validação ou um teste de erro e explique por que pertence àquela classe. Depois revise os podcasts sobre legado.

Não é preciso decorar cada API. É preciso explicar uma decisão, seu custo e como verificá-la. Se travar numa pergunta abaixo, volte à aula correspondente.

## 30 perguntas essenciais

### Sistema e documentação — aulas 01–03

1. **System Design, arquitetura e design de código diferem em quê?** São perspectivas sobrepostas: ecossistema e requisitos; decisões estruturais e restrições; organização e comportamento do código.
2. **Camadas lógicas exigem servidores separados?** Não. Um monólito pode ser modular. Distribuir processos também traz rede, falhas e operação.
3. **Latência e throughput são iguais?** Tempo de uma operação versus volume por unidade de tempo. Medir percentis e saturação ajuda a entender gargalos.
4. **Qual C4 vem primeiro?** Contexto para usuários e sistemas externos; containers para aplicações e dados. Mais detalhes entram quando ajudam a comunicar.
5. **O que contém um ADR?** Contexto, opções, decisão, consequências e status; pode ser substituído por uma decisão posterior.

### Dados e integração — aulas 04–06

6. **Como escolher um banco?** Acessos e invariantes do domínio, consistência, volume, operação e custo; não só o rótulo SQL/NoSQL.
7. **Qual a ressalva central de CAP?** Durante partição de rede há conflito entre disponibilidade e consistência forte nas definições do teorema; não se escolhem duas letras arbitrariamente.
8. **Quando uma fila ajuda?** Quando o produtor não precisa esperar o trabalho terminar, como geração de livros. Exige status, capacidade, retries e deduplicação.
9. **Cache pode decidir o saldo de uma compra?** Catálogo pode tolerar atraso; saldo e confirmação de pagamento precisam de invariantes duráveis.
10. **Quais falhas considerar numa chamada externa?** Timeout, erro, resultado desconhecido, duplicação e indisponibilidade prolongada; não só o cenário feliz.

### Código e DDD — aulas 07–12

11. **O que é alta coesão?** Agrupar responsabilidades que mudam pelos mesmos motivos, com limites compreensíveis.
12. **DIP é DI?** Não. DIP orienta dependências; DI fornece dependências. DI não garante bons contratos.
13. **Por que não criar interface para tudo?** O contrato precisa representar uma fronteira útil, não só duplicar uma classe.
14. **Quando Strategy ajuda?** Em variações reais de comportamento, como formatos de saída; não é obrigatório remover todo `if`.
15. **Observer substitui fila durável?** Não necessariamente. Eventos em memória e mensageria durável têm garantias diferentes.
16. **Entidade e Value Object diferem em quê?** Identidade ao longo do tempo versus igualdade pelos valores relevantes ao domínio.
17. **Qual o papel do Aggregate Root?** Controlar acesso e invariantes de um conjunto consistente, não apenas reunir listas.
18. **Por que o DDD estratégico vem antes?** Entender linguagem, contexto e negócio antes dos padrões táticos.
19. **Record é profundamente imutável?** Não. Campos finais podem referenciar objetos mutáveis. Cópias defensivas continuam importantes.
20. **O que jMolecules/ArchUnit garantem?** As regras configuradas e executadas; não a correção completa do modelo ou comportamento.

### Projeto e legado

21. **Como foi aplicado OCP?** Novo gerador implementa contrato e qualificador; o serviço não enumera classes concretas.
22. **Qual a lição de LSP no contador?** Herdar TreeMap expunha operações que quebravam o contrato de contagem; composição restringe a API.
23. **Qual a lição de ISP nos plugins?** Ganchos separados evitam métodos vazios obrigatórios.
24. **Maven e JPMS são iguais?** Maven organiza artefatos/build; JPMS controla leitura e exportação no module path. Fat JAR não conserva essas fronteiras automaticamente.
25. **Como comprovar encapsulamento?** Tentar importar um pacote não exportado a partir de outro módulo e verificar a falha esperada.
26. **Circuit breaker evita cobrança duplicada?** Não. Limita chamadas em falha. Duplicação exige identidade, controle atômico, persistência e contrato do provedor.
27. **Timeout significa pagamento não processado?** Não. Pode haver resultado desconhecido; consultar e reconciliar antes de outra cobrança.
28. **Por que não reescrever o legado por padrão?** Há valor e conhecimento acumulado. Testes de caracterização e evolução incremental reduzem risco; reescrita exige justificativa e migração.
29. **Sonar, OpenRewrite e IA dispensam revisão?** Não. Ajudam a analisar e mudar código, mas exigem testes e validação de requisitos.
30. **Quando o exercício termina?** Quando se consegue executá-lo, explicar escolhas, reproduzir testes e reconhecer limites; não apenas marcar um checklist.

## Prática

- [Instruções da branch](https://github.com/luis-jagella/java-pos-modulo-06/blob/projeto-pratico/projeto/README.md).
- [Validação executada](https://github.com/luis-jagella/java-pos-modulo-06/blob/projeto-pratico/projeto/VALIDACAO.md).
- [PR de integração](https://github.com/luis-jagella/java-pos-modulo-06/pull/1).

A avaliação do portal continua sob responsabilidade do aluno; nenhum resultado ou aprovação foi presumido.
