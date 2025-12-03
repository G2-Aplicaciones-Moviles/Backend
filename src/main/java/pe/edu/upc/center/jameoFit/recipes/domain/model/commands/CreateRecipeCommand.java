package pe.edu.upc.center.jameoFit.recipes.domain.model.commands;

public record CreateRecipeCommand(
        String name,
        String description,
        int preparationTime,
        String difficulty,
        Long categoryId,
        Long recipeTypeId,
        // Nuevos campos para manejar la lógica de plantillas/asignación
        Long createdByNutritionistId, // ID del nutricionista que la creó (null si es receta personal)
        Integer assignedToProfileId   // ID del perfil al que está asignada (null si es plantilla)
) {}