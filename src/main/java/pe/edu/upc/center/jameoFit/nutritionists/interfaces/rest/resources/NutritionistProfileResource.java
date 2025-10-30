package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.valueobjects.Specialty;

public record NutritionistProfileResource(
        String fullName,
        String licenseNumber,
        Specialty specialty,
        Integer yearsExperience
) {}
