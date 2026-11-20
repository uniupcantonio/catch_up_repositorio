# IceCube Platform

## Overview

IceCube Platform is a Spring Boot RESTful API that supports the operations of the IceCube Neutrino Observatory. It registers neutrino alerts raised by the observatory detectors so they can be followed up and analyzed by the scientific community.

The solution applies Domain-Driven Design with a `detection` bounded context and a `shared` bounded context, a layered architecture (domain, application, infrastructure and interfaces), CQRS commands, the `Result` pattern and JPA persistence on MySQL.

## Authorship

- Author: Carlos Cespedes
- Student code: u202416147
- NRC: 7750
- Repository: https://github.com/upc-pre-202620-1asi0729-pc2-7750/pc27750u202416147

## Features

- **POST /api/v1/neutrino-alerts**: registers a neutrino alert.
- **Interactive API docs**: Swagger UI at `/swagger-ui.html`.
- **Localized error messages**: English by default, Spanish when `Accept-Language` is `es` or `es-PE`.

## Business rules

- `alertCode` is unique, enforced by a pre-check and by the `uk_neutrino_alert_alert_code` UNIQUE constraint.
- `detectorId` must be a valid UUID that identifies a detector managed by another bounded context.
- `followUpEndDate` must be after `followUpStartDate`.
- `minimumEnergy` and `maximumEnergy` are expressed in TeV, must be greater than 0, and `maximumEnergy` must be greater than `minimumEnergy`.
- `confidenceScore` must be between 0 and 1, both included.
- `status` accepts `CANDIDATE`, `UNDER_REVIEW`, `VERIFIED` or `DISMISSED`, and defaults to `CANDIDATE` when not specified.
- Creation and last update dates are recorded through JPA auditing.

## Technologies

- Java 27 and Spring Boot 4.1.2
- Spring Web MVC, Spring Data JPA (Hibernate) and Jakarta Bean Validation
- MySQL
- SpringDoc OpenAPI / Swagger UI
- Lombok
- Apache Maven (bundled `./mvnw` wrapper)

## Prerequisites

- Java 27
- MySQL 8 or higher

## Configuration

The `dev` profile is active by default. Every variable is optional and falls back to a local MySQL server:

| Variable | Default |
|---|---|
| `DATABASE_URL` | `localhost` |
| `DATABASE_PORT` | `3306` |
| `DATABASE_NAME` | `icecube` |
| `DATABASE_USER` | `root` |
| `DATABASE_PASSWORD` | `123456789` |
| `PORT` | `8080` |

The `icecube` schema is created automatically on startup.

## Error responses

Errors are returned as RFC 9457 `ProblemDetail` bodies.

| Status | Cause |
|---|---|
| `400 Bad Request` | Invalid or malformed input data |
| `409 Conflict` | Duplicate `alertCode` |
| `500 Internal Server Error` | Unexpected error |

## Getting Started

1. Build the project:
   ```bash
   ./mvnw clean package
   ```
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Access the API:
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - Or send a request directly:
     ```bash
     curl -X POST http://localhost:8080/api/v1/neutrino-alerts \
          -H "Content-Type: application/json" \
          -d '{"alertCode": "IC-261121A", "detectorId": "3f2b8c1e-7d4a-4e9b-9c2f-1a5d6e7f8a9b", "followUpStartDate": "2026-11-21", "followUpEndDate": "2026-11-28", "minimumEnergy": 120.5, "maximumEnergy": 290.0, "confidenceScore": 0.85, "status": "CANDIDATE", "scientificNotes": "Track-like event compatible with a blazar flare"}'
     ```
