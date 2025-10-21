package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.NutritionistResource;

public class NutritionistResourceFromEntityAssembler {
    public static NutritionistResource toResourceFromEntity(Nutritionist e) {
        return new NutritionistResource(
                e.getUserId(),
                e.getFullName(),
                e.getLicenseNumber(),
                e.getSpecialty(),
                e.getYearsExperience(),
                e.isAcceptingNewPatients()
        );
    }
}
