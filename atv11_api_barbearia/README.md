# Sistema de Agendamento de Barbearia (Barber Connect API)

Este projeto consiste em uma API REST desenvolvida para modernizar o processo de marcação de horários em barbearias. O sistema substitui agendamentos manuais por uma solução automatizada que gerencia barbeiros, clientes e disponibilidades, evitando conflitos de horários e otimizando a agenda do estabelecimento.

## Tecnologias Utilizadas

  - **Java 21**
  - **Spring Boot 3.x**
  - **Spring Data JPA / Hibernate**
  - **H2 Database** (Banco de dados em memória para desenvolvimento e testes)
  - **Bean Validation (@Valid)**
  - **Maven** (Gerenciador de dependências e build)

## Arquitetura e Boas Práticas

A aplicação segue o padrão de arquitetura em camadas (Controller, Service, Repository), priorizando a manutenibilidade e a separação de responsabilidades:

  - **Model**: Entidades JPA com mapeamentos relacionais e validações de integridade.
  - **DTOs (Records)**: Implementação de objetos de transferência de dados para garantir a segurança das entidades de domínio.
  - **Service**: Camada responsável por isolar toda a lógica de negócio e validações de agendamento.
  - **Global Exception Handler**: Estrutura para captura e tratamento padronizado de erros da API.

## Regras de Negócio Implementadas

1.  **Unicidade de Agendamento**: Um barbeiro não pode possuir dois clientes agendados para o mesmo horário.
2.  **Validação de Horário de Trabalho**: O sistema impede agendamentos fora do expediente cadastrado para cada barbeiro.
3.  **Gestão de Disponibilidade**: Validação em tempo real de conflitos de horários antes da persistência de novos agendamentos.
4.  **Vínculo Cliente-Barbeiro**: Registro preciso de qual profissional realizará o serviço para cada cliente em uma data e hora específica.
5.  **Consistência de Dados**: Impedimento de duplicidade de cadastros sensíveis e garantia de integridade referencial entre as entidades.

## Testes Automatizados

O projeto utiliza **JUnit 5** e **MockMvc** para a execução de testes de integração, validando os endpoints e as restrições de agendamento.

Para rodar os testes:

```bash
./mvnw test
```

## Como Executar o Projeto

1.  Clone o repositório:

<!-- end list -->

```bash
git clone https://github.com/Gabriella-Pio/BarbeariaAPI.git
```

2.  Acesse a pasta do projeto e realize o build com Maven:

<!-- end list -->

```bash
./mvnw clean install
```

3.  Execute a aplicação:

<!-- end list -->

```bash
./mvnw spring-boot:run
```

A API estará disponível para acesso em http://localhost:8080.

-----

Desenvolvido por Gabriella Pio Correa