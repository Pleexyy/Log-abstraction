#!/bin/sh

# build the program

PROJDIR="$(pwd)"
OUTDIR="$PROJDIR/target/classes"

# copie les ressources
mkdir -p "$OUTDIR"
cp -rp ./src/main/resources/* "$OUTDIR"

# trouve les fichiers java et compile avec les dépendances
find ./src/main/java -name "*.java" -exec javac -classpath "$OUTDIR":$HOME/.m2/repository/com/github/haifengl/smile-core/2.6.0/smile-core-2.6.0.jar:$HOME/.m2/repository/com/github/haifengl/smile-math/2.6.0/smile-math-2.6.0.jar:$HOME/.m2/repository/com/github/haifengl/smile-graph/2.6.0/smile-graph-2.6.0.jar:$HOME/.m2/repository/com/github/haifengl/smile-data/2.6.0/smile-data-2.6.0.jar:$HOME/.m2/repository/org/slf4j/slf4j-simple/2.0.0-alpha4/slf4j-simple-2.0.0-alpha4.jar:$HOME/.m2/repository/org/slf4j/slf4j-api/2.0.0-alpha4/slf4j-api-2.0.0-alpha4.jar:$HOME/.m2/repository/com/github/haifengl/smile-plot/2.6.0/smile-plot-2.6.0.jar:$HOME/.m2/repository/org/swinglabs/swingx/1.6.1/swingx-1.6.1.jar:$HOME/.m2/repository/com/jhlabs/filters/2.0.235/filters-2.0.235.jar:$HOME/.m2/repository/org/swinglabs/swing-worker/1.1/swing-worker-1.1.jar:$HOME/.m2/repository/com/github/haifengl/smile-io/2.6.0/smile-io-2.6.0.jar:$HOME/.m2/repository/com/epam/parso/2.0.12/parso-2.0.12.jar:$HOME/.m2/repository/org/apache/commons/commons-csv/1.8/commons-csv-1.8.jar -d "$OUTDIR" "{}" +

