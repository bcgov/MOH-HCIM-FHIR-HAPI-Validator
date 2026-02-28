package hlth.gov.bc.ca.hapiValidator.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.InMemoryTerminologyServerValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.NpmPackageValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.RemoteTerminologyServiceValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;
import hlth.gov.bc.ca.hapiValidator.util.CustomStructureDefinitionValidationSupport;

@Service
public class HAPIValidatorService {

    private final FhirContext fhirContext;
    private final FhirValidator fhirValidator;

    public HAPIValidatorService() throws IOException {
        this.fhirContext = FhirContext.forR4();
        this.fhirValidator = getFhirValidator();
    }

    /**
     * Validates a FHIR resource (as a string) and returns the validation messages.
     * 
     * @param fhirResourceString The FHIR resource in string format.
     * @return A list of validation results for the given FHIR resource.
     */
    public List<String> validate(String fhirResourceString) {
        try {
            ValidationResult result = fhirValidator.validateWithResult(fhirResourceString);
            return result.getMessages().stream()
                .map(msg -> msg.getSeverity() + " | " + msg.getLocationString() + " | " + msg.getMessage())
                .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private FhirValidator getFhirValidator() throws IOException{
        FhirValidator validator = fhirContext.newValidator();
        FhirInstanceValidator instanceValidator = new FhirInstanceValidator(getValidationSupportChain());
        validator.registerValidatorModule(instanceValidator);
        return validator;
    }

    private ValidationSupportChain getValidationSupportChain() throws IOException {
        NpmPackageValidationSupport npmPackageValidationSupport = new NpmPackageValidationSupport(fhirContext);
        npmPackageValidationSupport.loadPackageFromClasspath("classpath:hl7.fhir.ca.baseline.tgz");
        return new ValidationSupportChain(
            npmPackageValidationSupport,
            new DefaultProfileValidationSupport(fhirContext),
            new InMemoryTerminologyServerValidationSupport(fhirContext),
            new CommonCodeSystemsTerminologyService(fhirContext),
            new RemoteTerminologyServiceValidationSupport(fhirContext, "https://terminology.hlth.gov.bc.ca/ClientRegistry/"),
            new CustomStructureDefinitionValidationSupport(fhirContext)
        );
    }

}
