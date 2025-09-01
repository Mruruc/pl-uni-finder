# FU-Search-Service: A Search Service for Client Applications

This service provides a RESTful API to perform search operations on a dataset.  
It communicates directly with a Typesense search engine, and its main workflow is decoupled from the database, ensuring efficient and scalable search functionality.

---

## Features

- **RESTful API**: Exposes a versioned API (v1) for all search operations.
- **Typesense Integration**: Seamlessly integrates with the Typesense search engine for fast, relevant results.
- **Configurable**: Easy configuration via environment variables.
- **Error Handling and Logging**: Robust mechanisms for handling exceptions and logging.
- **Dockerized**: Simplified deployment and scaling using Docker.

---

## Dependencies

- `spring-boot-starter-web`
- `spring-boot-starter-validation`
- `typesense-java` client

---

## Configuration

The application can be configured using environment variables or a profile-specific properties file.

```yaml
typesense:
  host: your-typesense-host
  port: 443
  protocol: https
  api-key: your-typesense-api-key
  connection-timeout: 10s
````
## Workflow

1. A client sends a search request to the `/api/v1/programs` endpoint.  
   The request includes query parameters: `query`, `page`, and `perPage`.

2. The controller receives and validates the `ProgramSearchRequest`.  
   It then forwards the request to the service layer.

3. The `query` parameter must not be empty; to search all programs, the query should be `"all"`.

4. The `page` and `perPage` parameters have default values of `1` and `10`, respectively.

5. The service layer delegates the search operation to the repository layer and maps the results to a `ProgramSearchResponse`.

6. The repository layer constructs the search query and interacts with the Typesense client to perform the search on the specified collection.

7. The Typesense client returns the search results, which are then mapped to the response object and returned to the client.

8. The client receives the search results in a structured format.


## Error Handling

The application includes robust error handling to manage exceptions and provide clients with clear, meaningful error responses.

---

## Sequence Diagram

```mermaid
sequenceDiagram
    participant Client
    participant Controller
    participant Service
    participant Repository
    participant Typesense
    
    Client->>Controller: Sends search request
    Controller->>Service: Forwards validated request
    Service->>Repository: Delegates search operation
    Repository->>Typesense: Performs search query
    Typesense-->>Repository: Returns search results
    Repository-->>Service: Returns mapped results
    Service-->>Controller: Returns response object
    Controller-->>Client: Sends search results
````

----

## Architecture Flowchart
```mermaid
flowchart LR
    A[Client] -->|Search Request| B[Controller]
    B --> C[Service Layer]
    C --> D[Repository Layer]
    D --> E[Typesense Engine]
    E --> D
    D --> C
    C --> B
    B --> A[Client]
```

---

## Summary

FU-Search-Service is designed for scalable, efficient search operations using Typesense.
It supports versioned RESTful APIs, robust error handling, and can be easily deployed using Docker.
The architecture decouples database operations from search functionality, ensuring high performance for client applications.