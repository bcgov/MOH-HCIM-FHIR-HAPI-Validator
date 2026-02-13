package hlth.gov.bc.ca.hapiValidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.uhn.fhir.context.FhirContext;

@SpringBootApplication
public class HAPIValidatorApplication {
    
    @Bean
    public FhirContext fhirContext() {
        return FhirContext.forR4();
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(HAPIValidatorApplication.class, args)));
    }

}