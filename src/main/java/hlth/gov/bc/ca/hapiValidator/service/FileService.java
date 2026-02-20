package hlth.gov.bc.ca.hapiValidator.service;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import hlth.gov.bc.ca.hapiValidator.util.Consts;

@Service
public class FileService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${spring.hapi.input.path}")
    private String inputPath;

    @Value("${spring.hapi.output.path}")
    private String outputPath;

    /**
     * Reads the content of an input file containing a request/response message and returns it as a string.
     * 
     * @param fileName The input file name.
     * @return The content of the input file as a string.
     */
    public String readMessage(String fileName) {
        try (InputStream inputStream = resourceLoader.getResource("file:" + inputPath + "/" + fileName).getInputStream()) {
            return (IOUtils.toString(inputStream, "UTF-8"));
            
        } catch (NullPointerException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Writes the validation results for an input file into a correpsonding output text file.
     * 
     * @param fileName The output file name.
     * @param inputFileName The name of the input file being validated to write as a header for these results.
     * @param validationResults The results to write into the output file.
     */
    public void writeResults(String fileName, String inputFileName, List<String> validationResults) {
        try {
            Path path = Paths.get(outputPath + "/" + fileName);
            Files.writeString(path, "\n" + inputFileName + ":\n" + Consts.LINE_SEPARATOR, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(path, validationResults, StandardOpenOption.APPEND);
            Files.writeString(path, Consts.LINE_SEPARATOR, StandardOpenOption.APPEND);

        } catch (InvalidPathException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
