package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.UpdateAvailabilityCommand;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.AvailabilityConfigResource;

public class UpdateAvailabilityCommandFromResourceAssembler {
    public static UpdateAvailabilityCommand toCommand(Long userId, AvailabilityConfigResource r) {
        return new UpdateAvailabilityCommand(userId, r.acceptingNewPatients());
    }
}
