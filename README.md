# ConstrucaoSoftwareI

Repositório de exercícios e projetos da disciplina de **Construção de Software I** - focado em **Programação Orientada a Objetos (POO)** em Java.

## 📂 Estrutura

```
atividade01/
├── abstracao/        # Conceito de abstração
├── encapsulamento/   # Proteção de dados e acesso controlado
├── heranca/          # Herança e hierarquia de classes
└── polimorfismo/     # Múltiplas implementações da mesma interface

pilaresPOO/
├── encapsulamento/   # Exemplo: Sistema de livros
└── heranca/          # Exemplo: Sistema de veículos
```

## 🚀 Como Usar

### Compilação
```bash
javac -d bin $(find . -name "*.java")
```

### Executar um exemplo
```bash
java -cp bin atividade01.abstracao.main
java -cp bin atividade01.encapsulamento.main
java -cp bin atividade01.heranca.main
java -cp bin atividade01.polimorfismo.main
java -cp bin pilaresPOO.heranca.main
```

## 📋 Conteúdo

Cada pasta contém exemplos práticos dos **4 Pilares da POO**:
- **Abstração** - Interfaces e classes abstratas
- **Encapsulamento** - Dados privados e controle de acesso
- **Herança** - Reutilização de código e hierarquias
- **Polimorfismo** - Comportamentos variados com mesma interface

## ⚙️ Requisitos

- Java Development Kit (JDK) 21+

## 📝 Observação

Este repositório está em desenvolvimento e será expandido durante o curso.
