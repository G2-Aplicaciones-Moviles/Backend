package pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.valueobjects.Specialty;

public record CreateNutritionistCommand(
        Long userId,
        String fullName,
        String licenseNumber,
        Specialty specialty,
        Integer yearsExperience,
        Boolean acceptingNewPatients,
        String bio,
        String profilePictureUrl
) {}
