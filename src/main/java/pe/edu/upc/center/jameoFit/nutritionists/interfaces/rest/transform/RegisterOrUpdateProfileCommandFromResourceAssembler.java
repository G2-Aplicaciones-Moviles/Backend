package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.RegisterOrUpdateProfileCommand;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.NutritionistProfileResource;

public class RegisterOrUpdateProfileCommandFromResourceAssembler {
    public static RegisterOrUpdateProfileCommand toCommand(Long userId, NutritionistProfileResource r) {
        return new RegisterOrUpdateProfileCommand(userId, r.fullName(), r.licenseNumber(), r.specialty(), r.yearsExperience());
    }
}
