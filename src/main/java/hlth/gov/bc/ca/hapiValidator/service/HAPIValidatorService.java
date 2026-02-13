package hlth.gov.bc.ca.hapiValidator.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;

@Service
public class HAPIValidatorService {

    @Autowired
    private FhirContext fhirContext;

    /**
     * Validates a FHIR resource (as a string) and returns the validation messages.
     * 
     * @param fhirResourceString The FHIR resource in string format.
     * @return A list of validation results for the given FHIR resource.
     */
    public List<String> validate(String fhirResourceString) {
        ValidationResult result = getFhirValidator().validateWithResult(fhirResourceString);
        return result.getMessages().stream()
            .map(msg -> msg.getSeverity() + " | " + msg.getLocationString() + " | " + msg.getMessage())
            .collect(Collectors.toList());
    }

    private FhirValidator getFhirValidator() {
        FhirValidator validator = fhirContext.newValidator();
        FhirInstanceValidator instanceValidator = new FhirInstanceValidator(getValidationSupportChain());
        validator.registerValidatorModule(instanceValidator);
        return validator;
    }

    private ValidationSupportChain getValidationSupportChain() {
        return new ValidationSupportChain(
            new DefaultProfileValidationSupport(fhirContext)
        );
    }

}
