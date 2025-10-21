package pe.edu.upc.center.jameoFit.nutritionists.application.internal.commandservices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.nutritionists.application.internal.outboundservices.acl.IamExternalService;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.RegisterOrUpdateProfileCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.UpdateAvailabilityCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.services.NutritionistCommandService;
import pe.edu.upc.center.jameoFit.nutritionists.infrastructure.persistence.jpa.repositories.NutritionistRepository;

@Service
@Transactional
public class NutritionistCommandServiceImpl implements NutritionistCommandService {

    private final NutritionistRepository repository;
    private final IamExternalService iamService;

    public NutritionistCommandServiceImpl(NutritionistRepository repository,
                                          IamExternalService iamService) {
        this.repository = repository;
        this.iamService = iamService;
    }

    @Override
    public Nutritionist handle(RegisterOrUpdateProfileCommand command) {
        if (!iamService.userExists(command.userId()))
            throw new IllegalArgumentException("User not found in IAM");

        var nutritionist = repository.findByUserId(command.userId())
                .orElseGet(() -> new Nutritionist(
                        command.userId(),
                        command.fullName(),
                        command.licenseNumber(),
                        command.specialty(),
                        command.yearsExperience()
                ));

        nutritionist.updateProfile(command.fullName(),
                command.licenseNumber(),
                command.specialty(),
                command.yearsExperience());

        return repository.save(nutritionist);
    }

    @Override
    public Nutritionist handle(UpdateAvailabilityCommand command) {
        var nutritionist = repository.findByUserId(command.userId())
                .orElseThrow(() -> new IllegalStateException("Nutritionist not found for userId=" + command.userId()));

        nutritionist.updateAvailability(command.acceptingNewPatients());
        return repository.save(nutritionist);
    }
}
