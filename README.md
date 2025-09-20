
# 📊 Siconfi Data Extractor - API Client

Este projeto é uma aplicação **Spring Boot** que consome a API oficial do Tesouro Nacional (Siconfi Data Lake) para extração de dados de demonstrativos fiscais (RREO, RGF, etc.).

---

## 🚀 Funcionalidades

- Exposição de endpoints REST para extração de dados do Siconfi.
- Consumo da API pública `https://apidatalake.tesouro.gov.br/ords/siconfi/tt`.
- Suporte a múltiplos parâmetros (ano, período, anexo, ente federado, etc.).
- Tratamento de erros e logging de requisições.

---

## 📂 Estrutura do Projeto

```
src/main/java/com/ggie/siconfi/
├── controller/      # Controladores REST (endpoints)
├── service/         # Regras de negócio e integração
├── util/            # Cliente RestTemplate para consumo da API Siconfi
└── model/           # Classes DTO e modelos de dados
```

---

## ⚙️ Endpoints Disponíveis

### 1️⃣ Teste de conexão
```http
GET /api/siconfi/dados
```
Retorna uma string de teste confirmando que a API está funcionando.

**Exemplo de resposta:**
```json
"Dados extraídos"
```

### 2️⃣ Extração de dados do Siconfi
```http
POST /api/siconfi/extrair
```

**Body (JSON):**
```json
{
  "anos": [2015],
  "periodos": [1],
  "documento": "RREO",
  "anexo": "01",
  "codEntes": ["2611606"],
  "nomeEntes": ["Prefeitura Municipal de Recife - PE"]
}
```

**Resposta (JSON simplificado):**
```json
[
  {
    "ano": 2015,
    "periodo": 1,
    "documento": "RREO",
    "anexo": "01",
    "codEnte": "2611606",
    "nomeEnte": "Prefeitura Municipal de Recife - PE",
    "data": { ... }
  }
]
```

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Web (RestTemplate)**
- **Maven**
- **Postman** (para testes)

---

## 🧪 Como Testar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/siconfi-extractor.git
   ```

2. Acesse a pasta do projeto e compile:
   ```bash
   mvn clean install
   ```

3. Rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Teste no Postman ou via `curl`:

   ```bash
   curl -X POST http://localhost:8081/api/siconfi/extrair    -H "Content-Type: application/json"    -d '{
         "anos": [2015],
         "periodos": [1],
         "documento": "RREO",
         "anexo": "01",
         "codEntes": ["2611606"],
         "nomeEntes": ["Prefeitura Municipal de Recife - PE"]
       }'
   ```

---

## ⚠️ Observações Importantes

- O campo `no_anexo` deve estar no formato **"RREO-Anexo 01"**, conforme exigência da API do Tesouro.
- Caso utilize valores incorretos, a API retornará **404 Not Found**.
- Sempre utilize o código do ente (`id_ente`) fornecido pelo Tesouro Nacional.

---

## 📜 Licença

Este projeto é distribuído sob a licença MIT. Livre para uso e adaptação.
