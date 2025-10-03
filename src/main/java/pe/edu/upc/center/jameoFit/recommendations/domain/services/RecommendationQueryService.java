package pe.edu.upc.center.jameoFit.recommendations.domain.services;

import pe.edu.upc.center.jameoFit.recommendations.domain.model.aggregates.Recommendation;
import pe.edu.upc.center.jameoFit.recommendations.domain.model.queries.GetRecommendationsByUserQuery;

import java.util.List;

public interface RecommendationQueryService {
    List<Recommendation> handle(GetRecommendationsByUserQuery query);
}
