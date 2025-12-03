package pe.edu.upc.center.jameoFit.nutritionists.application.internal.commandservices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.NutritionistPatient;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.CreateNutritionistPatientCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.ApprovePatientCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.DeleteNutritionistPatientCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.services.NutritionistPatientCommandService;
import pe.edu.upc.center.jameoFit.nutritionists.infrastructure.persistence.jpa.repositories.NutritionistPatientRepository;

import java.util.Optional;

@Service
@Transactional
public class NutritionistPatientCommandServiceImpl implements NutritionistPatientCommandService {

    private final NutritionistPatientRepository repository;

    public NutritionistPatientCommandServiceImpl(NutritionistPatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public NutritionistPatient handle(CreateNutritionistPatientCommand command) {
        var relation = new NutritionistPatient(
                command.nutritionistId(),
                command.patientUserId(),
                command.serviceType(),
                command.startDate(),
                command.scheduledAt()
        );
        return repository.save(relation);
    }

    @Override
    public Optional<NutritionistPatient> handle(ApprovePatientCommand command) {
        return repository.findById(command.relationId())
                .map(r -> {
                    r.approve();
                    return repository.save(r);
                });
    }

    @Override
    public Optional<NutritionistPatient> handle(DeleteNutritionistPatientCommand command) {
        return repository.findById(command.relationId())
                .map(r -> {
                    repository.delete(r);
                    return r;
                });
    }
}
