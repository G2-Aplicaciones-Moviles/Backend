package pe.edu.upc.center.jameoFit.mealplan.interfaces.rest.resources;

public record CreateMealPlanEntryResource(
        int recipeId,
        int day,
        int mealPlanTypeId
) {
}
