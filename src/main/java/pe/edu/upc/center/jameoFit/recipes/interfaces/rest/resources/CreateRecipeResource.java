package pe.edu.upc.center.jameoFit.recipes.interfaces.rest.resources;

public record CreateRecipeResource(
        Long userId,
        String name,
        String description,
        int preparationTime,
        String difficulty,
        Long categoryId,
        Long recipeTypeId
) {}