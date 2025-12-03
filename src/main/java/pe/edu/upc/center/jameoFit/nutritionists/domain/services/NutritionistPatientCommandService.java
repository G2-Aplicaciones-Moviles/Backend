package pe.edu.upc.center.jameoFit.nutritionists.domain.services;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.NutritionistPatient;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.CreateNutritionistPatientCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.ApprovePatientCommand;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands.DeleteNutritionistPatientCommand;

import java.util.Optional;

public interface NutritionistPatientCommandService {

    /**
     * Crea una relación nutricionista–paciente (solicitud de paciente)
     */
    NutritionistPatient handle(CreateNutritionistPatientCommand command);

    /**
     * Aprueba una solicitud pendiente
     */
    Optional<NutritionistPatient> handle(ApprovePatientCommand command);

    Optional<NutritionistPatient> handle(DeleteNutritionistPatientCommand command);
}
