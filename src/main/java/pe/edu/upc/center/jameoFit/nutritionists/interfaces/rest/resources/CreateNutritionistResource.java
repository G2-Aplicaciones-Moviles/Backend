package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.valueobjects.Specialty;

public record CreateNutritionistResource(
        Long userId,
        String fullName,
        String licenseNumber,
        Specialty specialty,
        Integer yearsExperience,
        Boolean acceptingNewPatients,
        String bio,
        String profilePictureUrl
) {}
