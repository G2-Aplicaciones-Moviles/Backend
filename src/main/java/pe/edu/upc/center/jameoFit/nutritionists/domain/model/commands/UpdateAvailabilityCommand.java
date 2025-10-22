package pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands;

public record UpdateAvailabilityCommand(
        Long userId,
        boolean acceptingNewPatients
) {}
