# Validação — 20/10/2026

Ambiente local: Windows, Temurin JDK 25.0.4.1+1, Maven 3.9.11. A JDK foi usada por processo; a instalação Java 17 do usuário não foi alterada globalmente.

## Resultados executados

| Verificação | Resultado |
| --- | --- |
| Reactor Maven: API, aplicação, Markdown, PDF, EPUB, HTML e CLI | Build e install local concluídos |
| Domínio | 2 testes, sem falhas |
| Arquitetura | 7 testes, sem falhas, incluindo entidade inválida detectada |
| Integração CLI | 5 testes, sem falhas |
| Plugin de estatísticas | 1 teste, sem falhas |
| JAR executável sem plugins | PDF, EPUB e HTML gerados |
| Plugins via classpath | Tema CSS e 46 palavras no exemplo |
| Plugins via module path JPMS | Tema e estatísticas encontrados por `uses/provides` |
| Encapsulamento JPMS | Compilação externa do gerador PDF recusada por pacote não exportado |
| Saída PDF de exemplo | Uma página renderizada e inspecionada visualmente, acentos legíveis |
| `git diff --check` | Sem erros |

São **15 testes automatizados**, além dos smoke tests de empacotamento. Os números não incluem uma aplicação web Cotubify, pois ela não foi implementada. O CI remoto tem resultado próprio no PR; não o presumimos a partir do teste local.

## Comandos utilizados

```bash
mvn -B -ntp -f projeto/cotuba/pom.xml clean install
mvn -B -ntp -f projeto/plugins/tema-css/pom.xml clean verify
mvn -B -ntp -f projeto/plugins/estatisticas/pom.xml clean verify
bash projeto/scripts/smoke.sh
```

O smoke usa uma pasta temporária nova e verifica conteúdo e extensões, a presença do tema e das estatísticas e a falha esperada de acesso a um pacote interno. A geração EPUB também passa por leitura ZIP e parsing XML nos testes de integração; EPUBCheck e testes em múltiplos leitores ficam fora do escopo validado.

## Problemas encontrados e corrigidos

- ArchUnit antigo não importava corretamente classes Java 25: dependências alinhadas a ArchUnit 1.5.1 e integração jMolecules 0.33.0. Há teste explícito verificando a importação de todos os módulos.
- A regra clássica de camadas não expressa a direção de dependências após DIP: a prática usa as regras hexagonais, com porta primária e adaptadores/portas secundárias.
- Proxy CDI no module path exigia `opens` dos contratos para o Weld: abertura restrita aos pacotes necessários, sem exportar os geradores concretos.
- A prova de encapsulamento fica fora dos módulos Maven normais, pois seu objetivo é **falhar** quando compilada separadamente.

Os avisos de dependências e os limites funcionais estão no [README da prática](README.md#limites-conhecidos).
