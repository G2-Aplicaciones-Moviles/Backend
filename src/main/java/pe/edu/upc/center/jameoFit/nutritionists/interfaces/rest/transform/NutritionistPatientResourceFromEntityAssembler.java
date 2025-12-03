package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.NutritionistPatient;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.NutritionistPatientResource;

public class NutritionistPatientResourceFromEntityAssembler {

    public static NutritionistPatientResource toResourceFromEntity(NutritionistPatient np) {
        return new NutritionistPatientResource(
                np.getId(),
                np.getNutritionistId(),
                np.getPatientUserId(),
                np.getServiceType(),
                np.getStartDate(),
                np.getScheduledAt(),
                np.getAccepted(),
                np.getRequestedAt()
        );
    }
}