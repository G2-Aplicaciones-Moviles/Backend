package pe.edu.upc.center.jameoFit.nutritionists.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.queries.GetNutritionistByUserQuery;
import pe.edu.upc.center.jameoFit.nutritionists.domain.services.NutritionistQueryService;
import pe.edu.upc.center.jameoFit.nutritionists.infrastructure.persistence.jpa.repositories.NutritionistRepository;

import java.util.Optional;

@Service
public class NutritionistQueryServiceImpl implements NutritionistQueryService {

    private final NutritionistRepository repository;

    public NutritionistQueryServiceImpl(NutritionistRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Nutritionist> handle(GetNutritionistByUserQuery query) {
        return repository.findByUserId(query.userId());
    }
}
