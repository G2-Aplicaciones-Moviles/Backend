package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources;

public record UpdateNutritionistResource(
        String fullName,
        String bio,
        String profilePictureUrl,
        Boolean acceptingNewPatients,
        Integer yearsExperience
) {}
