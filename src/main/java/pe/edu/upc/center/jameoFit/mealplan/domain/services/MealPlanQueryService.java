package pe.edu.upc.center.jameoFit.mealplan.domain.services;

import pe.edu.upc.center.jameoFit.mealplan.domain.model.aggregates.MealPlan;
import pe.edu.upc.center.jameoFit.mealplan.domain.model.queries.*;
import pe.edu.upc.center.jameoFit.mealplan.interfaces.rest.resources.MealPlanEntryDetailedResource;
import pe.edu.upc.center.jameoFit.recipes.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.center.jameoFit.recipes.interfaces.rest.resources.RecipeResource;

import java.util.List;
import java.util.Optional;

public interface MealPlanQueryService {
    Optional<MealPlan> handle(GetMealPlanByIdQuery query);
    List<MealPlan> handle(GetAllMealPlanQuery query);
    List<MealPlan> handle(GetAllMealPlanByProfileIdQuery query);
    List<MealPlanEntryDetailedResource> handle(GetEntriesWithRecipeInfo query);
    List<RecipeResource> handle(GetAllRecipesQuery query);
    List<MealPlan> handle(GetOriginalTemplatesQuery query);

}
