# Chat WebFlux com Angular

Este projeto demonstra a implementação de um chat em tempo real utilizando **Spring WebFlux** no backend e **Angular** no frontend. A comunicação entre os sistemas é feita de forma assíncrona usando **Server-Sent Events (SSE)**, garantindo atualizações instantâneas das mensagens sem a necessidade de polling contínuo.

📖 Leia o artigo completo sobre a implementação: [WebFlux e Angular: Comunicação em Tempo Real](http://wfreitas.dev/posts/webflux-e-angular/)

## Estrutura do Projeto

- `chat-webflux-backend/` → Backend desenvolvido com **Spring WebFlux** e **MongoDB**
- `chat-webflux-front/` → Frontend desenvolvido com **Angular**

## Como Executar

### 1️⃣ Backend (Spring WebFlux)

1. Certifique-se de ter o **Java 17+** e o **MongoDB** instalados
2. Acesse a pasta do backend:
   ```sh
   cd chat-webflux-backend
   ```
3. Execute a aplicação com:
   ```sh
   ./mvnw spring-boot:run
   ```
4. O backend estará disponível em `http://localhost:8080/chat`

### 2️⃣ Frontend (Angular)

1. Certifique-se de ter o **Node.js 18+** instalado
2. Acesse a pasta do frontend:
   ```sh
   cd chat-webflux-front
   ```
3. Instale as dependências:
   ```sh
   npm install
   ```
4. Inicie a aplicação:
   ```sh
   ng serve
   ```
5. Acesse `http://localhost:4200` no navegador

## Tecnologias Utilizadas

### Backend
- **Spring WebFlux** (Flux & Mono)
- **MongoDB** (ReactiveMongoRepository)
- **Event Publisher** para eventos assíncronos

### Frontend
- **Angular 16+**
- **RxJS** para manipulação de streams
- **EventSource** para SSE

---

