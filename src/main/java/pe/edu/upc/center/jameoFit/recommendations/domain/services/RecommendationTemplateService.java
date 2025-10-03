package pe.edu.upc.center.jameoFit.recommendations.domain.services;

import pe.edu.upc.center.jameoFit.recommendations.domain.model.entities.RecommendationTemplate;
import java.util.List;
import java.util.Optional;

public interface RecommendationTemplateService {
    List<RecommendationTemplate> getAllTemplates();
    Optional<RecommendationTemplate> findById(Long id);
}