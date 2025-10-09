package pe.edu.upc.center.jameoFit.tracking.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.profiles.interfaces.acl.ProfileContextFacade;
import pe.edu.upc.center.jameoFit.profiles.interfaces.acl.UserProfilesContextFacade;
import pe.edu.upc.center.jameoFit.tracking.domain.model.valueobjects.UserId;

import java.util.Optional;

/**
 * External User Profile Service (ACL)
 *
 * Capa Anti-Corrupción entre el bounded context Tracking y Profiles.
 * Permite al dominio Tracking acceder a datos del perfil del usuario
 * sin acoplarse al modelo interno de Profiles.
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

    /**
     * Verifica si existe un perfil asociado a un UserId.
     */
    public boolean existsByUserId(UserId userId) {
        return profileContextFacade.fetchAll().stream()
                .anyMatch(profile -> profile.userProfileId() == userId.userId());
    }

    /**
     * Obtiene el nombre del objetivo (goal) de un perfil de usuario.
     */
    public Optional<String> getObjectiveNameByProfileId(Long profileId) {
        return userProfilesFacade.fetchObjectiveNameByProfileId(profileId);
    }

    /**
     * Verifica si un perfil existe.
     */
    public boolean existsProfile(Long profileId) {
        return userProfilesFacade.existsProfileById(profileId);
    }

    /**
     * Lanza excepción si el perfil no existe.
     */
    public void validateProfileExists(Long profileId) {
        if (!existsProfile(profileId)) {
            throw new IllegalArgumentException("Profile not found with ID: " + profileId);
        }
    }

    /**
     * Obtiene el nombre del objetivo validando existencia.
     */
    public String getValidatedObjectiveName(Long profileId) {
        validateProfileExists(profileId);

        return getObjectiveNameByProfileId(profileId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Profile exists but has no objective defined for ID: " + profileId));
    }
}
