package pe.edu.upc.center.jameoFit.nutritionists.domain.services;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.RegisterOrUpdateProfileCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.UpdateAvailabilityCommand;

public interface NutritionistCommandService {
    Nutritionist handle(RegisterOrUpdateProfileCommand command);
    Nutritionist handle(UpdateAvailabilityCommand command);
}
