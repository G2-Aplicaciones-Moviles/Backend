package pe.edu.upc.center.jameoFit.recommendations.domain.services;

import pe.edu.upc.center.jameoFit.recommendations.domain.model.entities.RecommendationTemplate;

import java.util.List;

public interface RecommendationTemplateQueryService {
    List<RecommendationTemplate> getAllTemplates();
}