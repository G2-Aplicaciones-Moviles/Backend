package pe.edu.upc.center.jameoFit.tracking.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.profiles.interfaces.acl.ProfileContextFacade;
import pe.edu.upc.center.jameoFit.profiles.interfaces.acl.UserProfilesContextFacade;
import pe.edu.upc.center.jameoFit.profiles.domain.model.aggregates.UserProfile;
import pe.edu.upc.center.jameoFit.tracking.domain.model.dto.UserProfileDto;
import pe.edu.upc.center.jameoFit.tracking.domain.model.valueobjects.UserId;

import java.util.Optional;

/**
 * ACL entre Tracking y Profiles.
 * Provee métodos de validación y un mapeo a UserProfileDto que Tracking usa para cálculos.
 */
@Service
public class ExternalUserProfileService {

    private final UserProfilesContextFacade userProfilesFacade;
    private final ProfileContextFacade profileContextFacade;

    public ExternalUserProfileService(UserProfilesContextFacade userProfilesFacade,
                                      ProfileContextFacade profileContextFacade) {
        this.userProfilesFacade = userProfilesFacade;
        this.profileContextFacade = profileContextFacade;
    }

    public boolean existsByUserId(UserId userId) {
        return profileContextFacade.fetchAll().stream()
                .anyMatch(profile -> profile.userProfileId() == userId.userId());
    }

    public Optional<String> getObjectiveNameByProfileId(Long profileId) {
        return userProfilesFacade.fetchObjectiveNameByProfileId(profileId);
    }

    public boolean existsProfile(Long profileId) {
        return userProfilesFacade.existsProfileById(profileId);
    }

    public void validateProfileExists(Long profileId) {
        if (!existsProfile(profileId)) {
            throw new IllegalArgumentException("Profile not found with ID: " + profileId);
        }
    }

    public String getValidatedObjectiveName(Long profileId) {
        validateProfileExists(profileId);
        return getObjectiveNameByProfileId(profileId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Profile exists but has no objective defined for ID: " + profileId));
    }

    /**
     * Nuevo: devuelve el DTO simplificado para Tracking (map desde aggregate UserProfile).
     */
    public Optional<UserProfileDto> fetchUserProfileDtoById(Long profileId) {
        return userProfilesFacade.fetchUserProfileById(profileId)
                .map(this::mapToDto);
    }

    private UserProfileDto mapToDto(UserProfile up) {
        // activityFactor proviene de ActivityLevel entity (en UserProfile)
        double activityFactor = 1.2;
        if (up.getActivityLevel() != null) {
            activityFactor = up.getActivityLevel().getActivityFactor();
        }

        String objectiveName = up.getObjective() != null ? up.getObjective().getObjectiveName() : null;

        // up.getId() asumido Long; si en tu entity es Integer o int, ajusta UserProfileDto a Integer.
        Long userProfileId = null;
        try {
            userProfileId = (long) up.getId();
        } catch (Exception ignored) {
            // fallback: null (si tu getId() no está expuesto como Long)
        }

        return new UserProfileDto(
                userProfileId,
                up.getGender(),
                up.getHeight(),   // asumimos metros en entity
                up.getWeight(),
                activityFactor,
                objectiveName,
                up.getBirthDate()
        );
    }
}
