# MOH-HCIM-FHIR-HAPI-Validator

A utility tool to run a set of input FHIR messages through the HAPI FHIR Validation rules and the rules defined by the CA-Baseline, Implementation Guide, and Terminology Server

---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6.3+

### Clone
```bash
git clone https://github.com/bcgov/MOH-HCIM-FHIR-HAPI-Validator.git
```

### Build
```bash
cd MOH-HCIM-FHIR-HAPI-Validator
./get_Npm_CA_Baseline.sh
mvn clean install
```

### Run Locally
```bash
cd target
java -jar hapiValidator-1.0.jar
```

---

## Input Samples

The input-samples folder contains the current list of FHIR messages to run through validation. On build, these messages are copied into target/input

## Output Results

The results of each run is tracked in its own text file under target/output, using the current datetime that validation began for that run.

---

## Contacts

**Project Owner:** Joshua Burton
**Email:** [joshua.burton@cgi.com] 