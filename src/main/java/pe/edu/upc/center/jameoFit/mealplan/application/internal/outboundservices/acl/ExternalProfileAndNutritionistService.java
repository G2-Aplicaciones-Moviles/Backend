package pe.edu.upc.center.jameoFit.mealplan.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.profiles.interfaces.acl.UserProfilesContextFacade;
import pe.edu.upc.center.jameoFit.nutritionists.infrastructure.persistence.jpa.repositories.NutritionistRepository;

@Service
public class ExternalProfileAndNutritionistService {

    private final UserProfilesContextFacade userProfilesContextFacade;
    private final NutritionistRepository nutritionistRepository;

    public ExternalProfileAndNutritionistService(UserProfilesContextFacade userProfilesContextFacade,
                                                 NutritionistRepository nutritionistRepository) {
        this.userProfilesContextFacade = userProfilesContextFacade;
        this.nutritionistRepository = nutritionistRepository;
    }

    /** Validación para usuarios normales */
    public void validateUserProfile(Long userId) {
        if (!userProfilesContextFacade.existsProfileById(userId)) {
            throw new IllegalArgumentException(
                    "No regular user profile found with ID: " + userId
            );
        }
    }

    /** Validación para nutricionistas */
    public void validateNutritionist(Long userId) {
        boolean exists = nutritionistRepository.findByUserId(userId).isPresent();

        if (!exists) {
            throw new IllegalArgumentException(
                    "No nutritionist found with userId: " + userId
            );
        }
    }
}
