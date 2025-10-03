package pe.edu.upc.center.jameoFit.mealplan.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.mealplan.domain.model.commands.CreateMealPlanEntryCommand;
import pe.edu.upc.center.jameoFit.mealplan.domain.model.valueobjects.RecipeId;
import pe.edu.upc.center.jameoFit.mealplan.interfaces.rest.resources.CreateMealPlanEntryResource;

public class CreateMealPlanEntryCommandFromResourceAssembler {
    public static CreateMealPlanEntryCommand toCommandFromResource(CreateMealPlanEntryResource resource) {
        return new CreateMealPlanEntryCommand(
                new RecipeId(resource.recipeId()),
                resource.day(),
                resource.mealPlanTypeId()
        );
    }
}
