package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.CreateNutritionistCommand;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.CreateNutritionistResource;

public class CreateNutritionistCommandFromResourceAssembler {
    public static CreateNutritionistCommand toCommandFromResource(CreateNutritionistResource resource) {
        return new CreateNutritionistCommand(
                resource.userId(),
                resource.fullName(),
                resource.licenseNumber(),
                resource.specialty(),
                resource.yearsExperience(),
                resource.acceptingNewPatients(),
                resource.bio(),
                resource.profilePictureUrl()
        );
    }
}
