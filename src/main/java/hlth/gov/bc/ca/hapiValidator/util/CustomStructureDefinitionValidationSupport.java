package hlth.gov.bc.ca.hapiValidator.util;

import java.io.InputStream;
import java.net.URL;

import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.ValueSet;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

public class CustomStructureDefinitionValidationSupport extends PrePopulatedValidationSupport {

    public CustomStructureDefinitionValidationSupport(FhirContext context) {

        super(context);

        IParser jsonParser = context.newJsonParser();
        
        // Structure Definitions
        for (String url : IGDefinitions.structureDefinitionUrls) {
            try (InputStream inputStream = new URL(url).openStream()) {
                super.addStructureDefinition((StructureDefinition) jsonParser.parseResource(inputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Value Sets
        for (String url : IGDefinitions.valueSetUrls) {
            try (InputStream inputStream = new URL(url).openStream()) {
                super.addValueSet((ValueSet) jsonParser.parseResource(inputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Code Systems
        for (String url : IGDefinitions.codeSystemUrls) {
            try (InputStream inputStream = new URL(url).openStream()) {
                super.addCodeSystem((CodeSystem) jsonParser.parseResource(inputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
