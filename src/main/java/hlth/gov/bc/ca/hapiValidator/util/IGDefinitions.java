package hlth.gov.bc.ca.hapiValidator.util;

public class IGDefinitions {

    public static final String[] structureDefinitionUrls = new String[]{
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-patient.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-merge-patient.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-add-request-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-add-response-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-async-ack-response-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-find-candidates-request-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-get-demographics-request-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-merge-request-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-merge-response-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-revise-request-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-revise-response-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-search-response-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-metadata-parameter-async-response.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-metadata-parameters-in.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-metadata-parameters-out.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-HCIMPatientChangeSubscription.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-metadata-parameters-subscription.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-subscription-notification-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-update-patient.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-update-request-bundle.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-patient-by-example.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-update-patient-operation-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-validation-status-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-birthdate-history-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-business-period-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-death-date-history-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-death-verified-flag-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-death-verified-flag-history-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-gender-history-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-gender-identity-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-identifier-status-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-merge-status-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-multiplebirth-history-extension.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/StructureDefinition-bc-sourceId-extension.json"
    };
    public static final String[] valueSetUrls = new String[]{
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-address-validation-value-set.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-client-registry-patient-change-notification-events-value-set.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-contact-point-system-value-set.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-contact-point-use-value-set.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-identifier-status-value-set.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-merge-status-value-set.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-name-use-value-set.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/ValueSet-bc-client-registry-update-patient-operation-value-set.json"            
    };
    public static final String[] codeSystemUrls = new String[]{
        "https://bcgov.github.io/MOH-HCIM-FHIR/CodeSystem-bc-client-registry-address-validation-code-system.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/CodeSystem-bc-operation-outcome-details-code-system.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/CodeSystem-bc-client-registry-patient-change-notification-events.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/CodeSystem-bc-client-registry-merge-status-code-system.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/CodeSystem-bc-identifier-status-code-system.json",
        "https://bcgov.github.io/MOH-HCIM-FHIR/CodeSystem-bc-client-registry-update-patient-operation-code-system.json"
    };
}
