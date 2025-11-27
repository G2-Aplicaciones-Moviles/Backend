package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.CreateNutritionistPatientCommand;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.CreateNutritionistPatientResource;

public class CreateNutritionistPatientCommandFromResourceAssembler {

    public static CreateNutritionistPatientCommand toCommandFromResource(CreateNutritionistPatientResource r) {
        return new CreateNutritionistPatientCommand(
                r.nutritionistId(),
                r.patientUserId(),
                r.serviceType(),
                r.startDate(),
                r.scheduledAt()
        );
    }
}
