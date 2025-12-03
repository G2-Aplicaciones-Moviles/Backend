package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.DeleteNutritionistPatientCommand;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.DeleteNutritionistPatientResource;

public class DeleteNutritionistPatientCommandFromResourceAssembler {

    public static DeleteNutritionistPatientCommand toCommandFromResource(DeleteNutritionistPatientResource resource) {
        return new DeleteNutritionistPatientCommand(resource.id());
    }
}