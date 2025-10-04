package pe.edu.upc.center.jameoFit.goals.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.goals.domain.model.aggregates.Goal;
import pe.edu.upc.center.jameoFit.goals.interfaces.rest.resources.GoalResource;

public class GoalResourceFromEntityAssembler {
    public static GoalResource toResourceFromEntity(Goal e) {
        return new GoalResource(
                e.getUserId().getValue(),
                e.getObjective(),
                e.getTargetWeightKg(),
                e.getPace(),
                e.getDietPreset(),
                e.getProteinPct(),
                e.getCarbsPct(),
                e.getFatPct()
        );
    }
}