package pe.edu.upc.center.jameoFit.profiles.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.profiles.domain.model.aggregates.UserProfile;
import pe.edu.upc.center.jameoFit.profiles.interfaces.rest.resources.UserProfileResource;

import java.util.List;
import java.util.stream.Collectors;

public class UserProfileResourceFromEntityAssembler {

    public static UserProfileResource toResourceFromEntity(UserProfile entity) {
        return new UserProfileResource(
                entity.getId(), // ✅ era String, ahora int (coincide con record)
                entity.getGender(),
                entity.getHeight(),
                entity.getWeight(),
                entity.getUserScore(),
                entity.getActivityLevel().getId(), // ✅ getId() ya devuelve Long
                entity.getActivityLevel().getName(),
                entity.getObjective().getId(), // ✅ getId() ya devuelve int
                entity.getObjective().getObjectiveName(),
                entity.getAllergies().stream()
                        .map(a -> a.getName())
                        .collect(Collectors.toList())
        );
    }

    public static List<UserProfileResource> toResources(List<UserProfile> entities) {
        return entities.stream()
                .map(UserProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}
