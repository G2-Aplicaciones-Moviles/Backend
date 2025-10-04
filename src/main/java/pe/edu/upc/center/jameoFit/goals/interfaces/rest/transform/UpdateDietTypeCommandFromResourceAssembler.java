package pe.edu.upc.center.jameoFit.goals.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.goals.domain.model.commands.UpdateDietTypeCommand;
import pe.edu.upc.center.jameoFit.goals.interfaces.rest.resources.DietTypeConfigResource;

public class UpdateDietTypeCommandFromResourceAssembler {
    public static UpdateDietTypeCommand toCommand(Long userId, DietTypeConfigResource r) {
        return new UpdateDietTypeCommand(userId, r.preset());
    }
}