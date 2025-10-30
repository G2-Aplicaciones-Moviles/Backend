package pe.edu.upc.center.jameoFit.nutritionists.domain.services;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.CreateNutritionistCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.UpdateNutritionistCommand;

import java.util.Optional;

public interface NutritionistCommandService {
    Nutritionist handle(CreateNutritionistCommand command);
    Optional<Nutritionist> handle(UpdateNutritionistCommand command);
}
