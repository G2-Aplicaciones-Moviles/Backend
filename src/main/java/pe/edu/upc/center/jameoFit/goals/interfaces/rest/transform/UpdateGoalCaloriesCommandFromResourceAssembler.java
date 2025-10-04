package pe.edu.upc.center.jameoFit.goals.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.goals.domain.model.commands.UpdateGoalCaloriesCommand;
import pe.edu.upc.center.jameoFit.goals.interfaces.rest.resources.GoalCalorieConfigResource;

public class UpdateGoalCaloriesCommandFromResourceAssembler {
    public static UpdateGoalCaloriesCommand toCommand(Long userId, GoalCalorieConfigResource r) {
        return new UpdateGoalCaloriesCommand(userId, r.objective(), r.targetWeightKg(), r.pace());
    }
}