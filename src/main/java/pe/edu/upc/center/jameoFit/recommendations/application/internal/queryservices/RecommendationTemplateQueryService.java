package pe.edu.upc.center.jameoFit.recommendations.application.internal.queryservices;

import pe.edu.upc.center.jameoFit.recommendations.domain.model.entities.RecommendationTemplate;
import pe.edu.upc.center.jameoFit.recommendations.infrastructure.persistence.jpa.repositories.RecommendationTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationTemplateQueryService implements pe.edu.upc.center.jameoFit.recommendations.domain.services.RecommendationTemplateQueryService {

    private final RecommendationTemplateRepository repository;

    public RecommendationTemplateQueryService(RecommendationTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RecommendationTemplate> getAllTemplates() {
        return repository.findAll();
    }
}
