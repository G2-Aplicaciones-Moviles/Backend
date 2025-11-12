package pe.edu.upc.center.jameoFit.recipes.domain.model.commands;

public record CreateRecipeCommand(
        Long userId,
        String name,
        String description,
        int preparationTime,
        String difficulty,
        Long categoryId,
        Long recipeTypeId
) {}
