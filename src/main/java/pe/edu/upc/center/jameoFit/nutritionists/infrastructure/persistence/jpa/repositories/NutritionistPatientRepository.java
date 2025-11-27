package pe.edu.upc.center.jameoFit.nutritionists.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.NutritionistPatient;

import java.util.List;
import java.util.Optional;

@Repository
public interface NutritionistPatientRepository extends JpaRepository<NutritionistPatient, Long> {

    List<NutritionistPatient> findByNutritionistId(Integer nutritionistId);

    List<NutritionistPatient> findByPatientUserId(Long userId);

    Optional<NutritionistPatient> findByNutritionistIdAndPatientUserId(Integer nutritionistId, Long userId);
}
