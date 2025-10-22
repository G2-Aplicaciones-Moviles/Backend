package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.UpdateNutritionistCommand;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.UpdateNutritionistResource;

public class UpdateNutritionistCommandFromResourceAssembler {
    public static UpdateNutritionistCommand toCommandFromResource(Long nutritionistId, UpdateNutritionistResource r) {
        return new UpdateNutritionistCommand(
                nutritionistId,
                r.fullName(),
                r.bio(),
                r.profilePictureUrl(),
                r.acceptingNewPatients(),
                r.yearsExperience()
        );
    }
}
