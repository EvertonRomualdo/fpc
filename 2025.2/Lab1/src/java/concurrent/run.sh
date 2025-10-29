#!/bin/bash

# Define o diretório base do script
BASE_DIR=$(dirname -- "$( readlink -f -- "$0"; )")

# Nome da classe principal (ajuste se for diferente)
MAIN_CLASS="SimpleConcurrentSolution"

# Verifica se os argumentos esperados foram passados
# (Ajuste a quantidade '3' se seu programa esperar mais ou menos argumentos)
if [ "$#" -lt 3 ]; then
  echo "Uso: $0 <num_tarefas> <tamanho_tarefa> <num_threads>"
  # Define valores padrão se nenhum argumento for fornecido
  # (Estes são os valores padrão do run_all.sh original para este lab)
  ARGS=("100" "1000" "4")
  echo "Usando argumentos padrão: ${ARGS[@]}"
else
  # Usa os argumentos passados para o script
  ARGS=("$@")
fi

# Chama o programa java
# -cp "$BASE_DIR/bin": Define o classpath (onde procurar .class), com aspas
# $MAIN_CLASS: Nome da classe principal
# "${ARGS[@]}": Passa todos os argumentos para o programa Java, com aspas
echo "Executando $MAIN_CLASS com argumentos: ${ARGS[@]}"
time java -cp "$BASE_DIR/bin" "$MAIN_CLASS" "${ARGS[@]}"