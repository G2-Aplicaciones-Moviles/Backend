package pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.valueobjects.Specialty;

public record RegisterOrUpdateProfileCommand(
        Long userId,
        String fullName,
        String licenseNumber,
        Specialty specialty,
        Integer yearsExperience
) {}
