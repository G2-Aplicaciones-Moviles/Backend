package pe.edu.upc.center.jameoFit.recipes.aplication.internal.outboundedservices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.profiles.interfaces.acl.UserProfilesContextFacade;
import pe.edu.upc.center.jameoFit.tracking.interfaces.acl.TrackingContextFacade;
import pe.edu.upc.center.jameoFit.recipes.domain.model.valueobjects.UserId;
import pe.edu.upc.center.jameoFit.recipes.domain.model.valueobjects.MacronutrientValuesId;
import pe.edu.upc.center.jameoFit.nutritionists.infrastructure.persistence.jpa.repositories.NutritionistRepository;

import java.util.Optional;

@Service
public class ExternalProfileAndTrackingService {

    private final UserProfilesContextFacade userProfilesContextFacade;
    private final NutritionistRepository nutritionistRepository;
    private final TrackingContextFacade trackingContextFacade;

    public ExternalProfileAndTrackingService(
            UserProfilesContextFacade userProfilesContextFacade,
            NutritionistRepository nutritionistRepository,
            @Lazy TrackingContextFacade trackingContextFacade) {

        this.userProfilesContextFacade = userProfilesContextFacade;
        this.nutritionistRepository = nutritionistRepository;
        this.trackingContextFacade = trackingContextFacade;
    }

    public String getNutritionistNameOrDefault(Long userId) {
        return nutritionistRepository.findByUserId(userId)
                .map(Nutritionist::getFullName)
                .orElse("Autor Desconocido (ID: " + userId + ")");
    }
    public void validateUserProfile(UserId userId) {
        if (!userProfilesContextFacade.existsProfileById(userId.userId())) {
            throw new IllegalArgumentException(
                    "No regular user profile found with ID: " + userId.userId()
            );
        }
    }

    public void validateNutritionist(UserId userId) {
        boolean exists = nutritionistRepository.findByUserId(userId.userId()).isPresent();
        if (!exists) {
            throw new IllegalArgumentException(
                    "No nutritionist found with userId: " + userId.userId()
            );
        }
    }


    public Optional<String> getObjectiveNameByUserId(UserId userId) {

        // Solo los USER profiles tienen objectiveName.
        // Los nutricionistas NO, así que devolvemos empty.
        if (!userProfilesContextFacade.existsProfileById(userId.userId())) {
            return Optional.empty();
        }

        return userProfilesContextFacade.fetchObjectiveNameByProfileId(userId.userId());
    }

    public String getValidatedObjectiveName(UserId userId) {

        // Intentamos obtener objetivo solo para usuarios normales
        return getObjectiveNameByUserId(userId)
                .orElse("No objective defined (nutritionist or user with no objective)");
    }

    public void validateMacronutrientValuesExists(MacronutrientValuesId macronutrientValuesId) {
        if (!trackingContextFacade.validateMacronutrientValuesExists(macronutrientValuesId.macronutrientValuesId())) {
            throw new IllegalArgumentException(
                    "MacronutrientValues not found with ID: " + macronutrientValuesId.macronutrientValuesId());
        }
    }
}
