package pe.edu.upc.center.jameoFit.nutritionists.domain.services;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.queries.GetNutritionistByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface NutritionistQueryService {
    Optional<Nutritionist> handle(GetNutritionistByUserIdQuery query);
    List<Nutritionist> handleAll();
}
