#!/usr/bin/env bash
set -euo pipefail
repo="$(cd "$(dirname "$0")/../.." && pwd)"
cd "$repo"
saida="$(mktemp -d)"
sep=:
case "$(uname -s)" in MINGW*|MSYS*|CYGWIN*) sep=';' ;; esac
jar='projeto/cotuba/cli/target/cotuba-cli-1.0-SNAPSHOT.jar'
java -jar "$jar" -d projeto/exemplo -f pdf -o "$saida/livro.pdf"
java -jar "$jar" -d projeto/exemplo -f epub -o "$saida/livro.epub"
java -jar "$jar" -d projeto/exemplo -f html -o "$saida/site"
test -s "$saida/livro.pdf" && test -s "$saida/livro.epub"
grep -q '001.html' "$saida/site/index.html"
cpplugins="${jar}${sep}projeto/plugins/tema-css/target/tema-css-1.0-SNAPSHOT.jar${sep}projeto/plugins/estatisticas/target/estatisticas-1.0-SNAPSHOT-all.jar"
java -cp "$cpplugins" dev.jagella.cotuba.cli.Main -d projeto/exemplo -f html -o "$saida/plugins" | tee "$saida/plugins.log"
grep -q 'Estat' "$saida/plugins.log"
grep -q '#174ea6' "$saida/plugins/001.html"
mvn -q -f projeto/cotuba/pom.xml -pl cli dependency:copy-dependencies -DincludeScope=runtime -Dmdep.overWriteSnapshots=true
mvn -q -f projeto/plugins/estatisticas/pom.xml dependency:copy-dependencies -DincludeScope=runtime
mp="projeto/cotuba/cli/target/dependency${sep}projeto/cotuba/cli/target/original-cotuba-cli-1.0-SNAPSHOT.jar${sep}projeto/plugins/tema-css/target/tema-css-1.0-SNAPSHOT.jar${sep}projeto/plugins/estatisticas/target/estatisticas-1.0-SNAPSHOT.jar${sep}projeto/plugins/estatisticas/target/dependency"
java --module-path "$mp" --module cotuba.cli/dev.jagella.cotuba.cli.Main -d projeto/exemplo -f html -o "$saida/jpms" | tee "$saida/jpms.log"
grep -q 'Estat' "$saida/jpms.log"
grep -q '#174ea6' "$saida/jpms/001.html"
if javac -J-Duser.language=en -J-Duser.country=US --module-path projeto/cotuba/cli/target/dependency -d "$saida/prova" projeto/verificacao-acesso/module-info.java projeto/verificacao-acesso/Prova.java > "$saida/prova.log" 2>&1; then
    echo 'ERRO: implementação interna acessível pelo JPMS' >&2
    exit 1
fi
grep -q 'does not export' "$saida/prova.log"
echo "Smoke tests e prova negativa do JPMS passaram. Saídas: $saida"
