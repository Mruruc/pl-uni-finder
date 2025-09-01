# FU-Uni-Service (Find University - University Service)

**FU-Uni-Service** is a sub-service of **Find University (FU)**.  
Its primary role is to manage **universities and their programs**, exposing RESTful APIs for CRUD operations.

⚠️ **Note:** This service does **not** provide search functionality.  
Search capabilities are handled by the **FU-Search-Service**.  
However, FU-Uni-Service is responsible for **indexing newly created or updated program data into Typesense**, enabling
efficient search in FU-Search-Service.

---

## Business Features

- Manage **universities**
    - Create, read, update, and delete university data.
- Manage **programs**
    - Create, read, update, and delete programs linked to universities.
    - Handle program requirements (academic, language, etc.).
    - Manage tags for categorizing programs.
- Ensure data consistency with validation and error handling.
- Automatic indexing of **programs** into Typesense for search.
    - Indexing is **asynchronous** and **non-blocking** to ensure high performance.
- Provides the foundational university and program data consumed by FU-Search-Service.

---

## Technical Features

- **RESTful API** endpoints for:
    - Universities
    - Programs
    - Program requirements
    - Tags
- **Database integration** for persistent storage of universities, programs, requirements, and tags.
- **Spring Domain Events** and **event listeners** used to decouple program persistence from indexing.
- **Asynchronous indexing pipeline**:
    - Separate thread pool ensures indexing does not block API requests.
    - Indexed data is stored in **Typesense** for efficient full-text search.
- **Validation** of incoming requests using Spring Validation.
- **Migration management** with Flyway.
- **Dockerized** for containerized deployment and scalability.

---

## Technologies Used

- **Language & Frameworks**
    - Java 21
    - Spring Boot (Web, Validation, Data JPA)
    - Hibernate ORM
- **Database**
    - PostgreSQL
    - Flyway (migrations)
- **Search Engine**
    - Typesense (Java client)
- **Build & Deployment**
    - Gradle
    - Docker
