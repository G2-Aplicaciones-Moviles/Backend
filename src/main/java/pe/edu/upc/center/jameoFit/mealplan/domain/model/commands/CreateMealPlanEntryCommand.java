package pe.edu.upc.center.jameoFit.mealplan.domain.model.commands;

import pe.edu.upc.center.jameoFit.mealplan.domain.model.valueobjects.RecipeId;

public record CreateMealPlanEntryCommand(
        RecipeId recipeId,
        int day,
        int mealPlanTypeId
) {
}


