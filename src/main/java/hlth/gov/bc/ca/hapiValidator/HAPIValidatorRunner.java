package hlth.gov.bc.ca.hapiValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hlth.gov.bc.ca.hapiValidator.service.FileService;
import hlth.gov.bc.ca.hapiValidator.service.HAPIValidatorService;

@Component
public class HAPIValidatorRunner implements CommandLineRunner {

    @Autowired
    private HAPIValidatorService hapiValidatorService;

    @Autowired
    private FileService fileService;

    @Value("${spring.hapi.input.path}")
    private String inputPath;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("HAPI Validator is running...");

        Path path = Paths.get(inputPath);
        if (!path.toFile().exists() || !path.toFile().isDirectory()) {
            System.err.println("Input path does not exist or is not a directory: " + inputPath);
            return;
        }
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(Files::isRegularFile)
                  .filter(file -> file.toString().endsWith(".json"))
                  .forEach(file -> {
                String fileName = file.getFileName().toString();
                System.out.println("Processing file: " + fileName);
                String message = fileService.readMessage(fileName);
                if (message != null) {
                    List<String> validationResults = hapiValidatorService.validate(message);
                    fileService.writeResults("validation_results.txt", fileName, validationResults);
                } else {
                    System.err.println("Failed to read message from file: " + fileName);
                }
            });
            System.out.println("Validation completed for all files in the directory.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
