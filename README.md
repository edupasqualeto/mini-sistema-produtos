# Mini Sistema de Produtos

Sistema desenvolvido em Java com foco em aprendizado de backend, aplicando conceitos de arquitetura em camadas, persistência de dados e boas práticas de programação.

## Funcionalidades

- Cadastro de produtos com validação de dados (ex: nome obrigatório)
- Listagem de produtos formatada em tabela
- Atualização de: Nome / Preço
- Remoção de produtos
- Aplicação de desconto
- Prevenção de produtos duplicados
- Persistência de dados com banco (JDBC + SQLite)
- Tratamento básico de erros (ex: conexões e queries)

## Tecnologias

- Java
- Programação Orientada a Objetos (POO)
- JDBC (Java Database Connectivity)
- SQLite
- Arquitetura em camadas

## Estrutura do projeto

miniSistema/
- model/       → Representação dos dados (Produto)
- service/     → Regras de negócio e validações
- database/    → Conexão e operações com o banco
- view/        → Interface com o usuário (menu)
- main/        → Classe principal para execução

## Objetivo

- Aplicar separação de responsabilidades (arquitetura em camadas)
- Praticar lógica de negócio
- Trabalhar com persistência de dados (CRUD completo)
- Boas práticas de código em Java
- Estrutura semelhante a sistemas reais backend

## Como rodar
1. Clone o projeto
- git clone <url-do-repositorio>
2. Compile
- javac Main.java
3. Execute a classe Main
- java Main

## Observações
- O banco de dados SQLite é criado automaticamente (caso não exista)
- Projeto focado em aprendizado — não segue ainda padrões avançados como frameworks (Spring, etc.)
