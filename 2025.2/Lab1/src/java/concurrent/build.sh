#!/bin/bash

# Define o diretório base do script
BASE_DIR=$(dirname -- "$( readlink -f -- "$0"; )")

# Cria o diretório 'bin' se ele não existir (USANDO ASPAS!)
mkdir -p "$BASE_DIR/bin"

# Compila todos os arquivos .java no diretório atual
# e coloca os arquivos .class na pasta 'bin' (USANDO ASPAS!)
echo "Compilando arquivos Java em $BASE_DIR..."
javac -d "$BASE_DIR/bin" "$BASE_DIR"/*.java

# Verifica se a compilação falhou
if [ $? -ne 0 ]; then
  echo "Erro na compilação!"
  exit 1
fi

echo "Compilação concluída."
