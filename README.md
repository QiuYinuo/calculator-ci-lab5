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
