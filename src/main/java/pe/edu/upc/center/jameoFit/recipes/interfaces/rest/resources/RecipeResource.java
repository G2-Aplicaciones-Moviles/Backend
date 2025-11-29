package pe.edu.upc.center.jameoFit.recipes.interfaces.rest.resources;

import java.util.List;

public record RecipeResource(
        int id,
        Long userId,
        String name,
        String description,
        int preparationTime,
        String difficulty,
        String category,
        String recipeType,
        List<RecipeIngredientResource> recipeIngredients
) {}


