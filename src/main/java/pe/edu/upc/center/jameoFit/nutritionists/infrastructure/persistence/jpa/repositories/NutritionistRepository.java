package pe.edu.upc.center.jameoFit.nutritionists.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;

import java.util.Optional;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {
    Optional<Nutritionist> findByUserId(Long userId);
}
