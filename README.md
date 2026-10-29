# Calculator CI Lab 5

[![Calculator CI](https://github.com/QiuYinuo/calculator-ci-lab5/actions/workflows/ci-calculator.yml/badge.svg)](https://github.com/QiuYinuo/calculator-ci-lab5/actions/workflows/ci-calculator.yml)

Java command line calculator continued from Lab 3 and Lab 4.

## Build and run

Requires JDK 17 or newer and Maven.

```bash
mvn clean test
mvn package
java -jar target/calculator-1.0.jar 2 + 2
java -jar target/calculator-1.0.jar 3 "*" 4
```

The calculator supports addition, subtraction, multiplication and division.
It rejects zero divisors, blank operands and invalid operators.

## Continuous integration

`.github/workflows/ci-calculator.yml` automatically checks pushes to `main`
and pull requests targeting `main` on Ubuntu with JDK 17 and 21.
Each job restores the Maven cache, resolves dependencies, compiles the source,
and executes all 13 JUnit 5 tests.

The Lab 5 history includes a deliberately broken subtraction commit followed
by a repair. Failed runs are retained as evidence that CI catches regressions.
The final source contains the correct subtraction implementation.

## Lab 4 analysis

```bash
mvn verify
mvn pmd:pmd
```

Open `target/site/jacoco/index.html` for coverage and
`target/reports/pmd.html` for static analysis findings.

## Lab 7: Docker Compose

The original calculator and JUnit tests now live in `backend/`.
The Java API listens on port 8000; nginx serves `web/index.html` on port 8080
and proxies `/api/` to `http://backend:8000` using Compose service DNS.

Run `docker compose up -d --build`, then open http://127.0.0.1:8080.
Inspect `docker compose ps` and `docker compose logs`.
Stop and remove both services with `docker compose down`.
`depends_on` orders startup but does not wait for API readiness; retry an initial request if needed.
Tests: `mvn -f backend/pom.xml clean test`.
The root Dockerfile retains the Lab 6 CLI; backend/Dockerfile starts the API.
