package pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands;

public record UpdateNutritionistCommand(
        Long nutritionistId,
        String fullName,
        String bio,
        String profilePictureUrl,
        Boolean acceptingNewPatients,
        Integer yearsExperience
) {}
