# FU-SYNC-SERVICE `(Find Universities Sync Service)`

## Initial Data Loading from PostgreSQL to Typesense with Spring Boot

The main application data is stored in **PostgreSQL** and **Typesense** is used as the search engine.  
For new inserts, updates, or deletes, the system already propagates changes to Typesense via **Spring Boot domain events**.

The `FU-SYNC-SERVICE` addresses the need for **initial bulk indexing** of existing PostgreSQL data into Typesense.

The service is designed as a **Spring Boot microservice** that supports:

- **One-time bulk export** of PostgreSQL data into Typesense collections.
- **Configurable reindexing** (on-demand or scheduled).
- **Performance optimizations** such as batching and parallelization.
- **Idempotency**, ensuring safe re-runs without creating duplicate records.

---

### How It Works

1. The service fetches data from PostgreSQL in pages/batches.
2. Entities are transformed into Typesense-compatible JSON objects.
3. Records are indexed efficiently using the **Typesense bulk import API**.

---

### Implementation Details (Spring Boot)

- Use **Spring Data JDBC** to fetch records.
- Use the **Typesense Java Client** (`org.typesense:typesense-java`) for indexing.
- Batch processing is handled using limits and offsets to improve performance.

---

## High-Level Architecture

```mermaid
flowchart LR
    A[PostgreSQL DB] -->|JDBC Fetch Paged| B[Spring Boot Indexing Service]
    B -->|Transform to JSON| C[Typesense Java Client]
    C -->|Bulk Import API| D[Typesense Cluster]
````