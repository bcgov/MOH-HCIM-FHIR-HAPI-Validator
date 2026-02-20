package hlth.gov.bc.ca.hapiValidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HAPIValidatorApplication {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(HAPIValidatorApplication.class, args)));
    }
}