# 🏥 Sistema de Clínica Médica - API REST

Este projeto é uma API REST para gerenciamento de pacientes e fichas médicas, desenvolvida com **Spring Boot**, **Spring Data JPA** e banco de dados **H2**. O objetivo é explorar relacionamentos `@OneToOne` e a separação entre dados cadastrais e clínicos.

## 🚀 Como Rodar o Projeto

1. **Pré-requisitos**: Ter o Java 21 instalado.
2. **Navegue até a pasta**: `atv08_clinica_medica_spring`.
3. **Execute a aplicação**:
   - **Linux/Mac**: `./mvnw spring-boot:run`
   - **Windows**: `mvnw.cmd spring-boot:run`
4. **Acesso**: A API estará disponível em `http://localhost:8080`.

---

## 🛠️ Tecnologias e Configuração

- **Framework**: Spring Boot 3.5.11.
- **Persistência**: Spring Data JPA com Hibernate.
- **Banco de Dados**: H2 (Baseada em arquivo em `./data/db-api`).
- **Console H2**: Acessível em `/h2-console` (JDBC URL: `jdbc:h2:file:./data/db-api`).
- **Validação**: Bean Validation para integridade de dados.

---

## 📍 Endpoints da API (Fase 1)

### 👤 Pacientes

- **Cadastrar**: `POST /api/pacientes`
- **Listar Todos**: `GET /api/pacientes`
- **Buscar por ID**: `GET /api/pacientes/{id}`
- **Buscar Completo (com Ficha)**: `GET /api/pacientes/{id}/completo`
- **Buscar por CPF**: `GET /api/pacientes/cpf/{cpf}`
- **Atualizar Dados**: `PUT /api/pacientes/{id}`
- **Inativar (Soft Delete)**: `DELETE /api/pacientes/{id}`
- **Alterar Status**:
  - `PATCH /api/pacientes/{id}/ativar`
  - `PATCH /api/pacientes/{id}/inativar`
  - `PATCH /api/pacientes/{id}/suspender`
- **Busca e Filtros**:
  - `GET /api/pacientes/buscar?termo={valor}` (Busca por nome ou e-mail)
  - `GET /api/pacientes/com-ficha` (Apenas pacientes com prontuário)
  - `GET /api/pacientes/sem-ficha` (Pacientes aguardando prontuário)

### 📄 Ficha Médica

- **Vincular Ficha**: `POST /api/pacientes/{id}/ficha`
- **Ver Ficha**: `GET /api/pacientes/{id}/ficha`
- **Atualizar Ficha**: `PUT /api/pacientes/{id}/ficha`
- **Remover Ficha**: `DELETE /api/pacientes/{id}/ficha`
- **Filtrar por Tipo Sanguíneo**: `GET /api/pacientes/tipo-sanguineo/{tipo}`

---

## 🧪 Exemplos de JSON para Teste

**Criar Paciente (`POST /api/pacientes`):**

```json
{
  "nome": "Fulano de Tal",
  "cpf": "12345678901",
  "email": "fulano@exemplo.com",
  "telefone": "62999999999",
  "dataNascimento": "2000-01-01",
  "sexo": "MASCULINO"
}
```

**Ficha Médica (POST /api/pacientes/1/ficha):**

```json
{
  "tipoSanguineo": "AB_POS",
  "alergias": "Dipirona",
  "medicamentosUso": "Nenhum",
  "historicoDoencas": "Nenhum",
  "observacoesClinicas": "Paciente estável"
}
```
