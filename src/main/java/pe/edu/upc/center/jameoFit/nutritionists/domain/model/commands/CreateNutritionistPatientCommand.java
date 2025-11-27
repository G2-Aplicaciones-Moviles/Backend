package pe.edu.upc.center.jameoFit.nutritionists.domain.model.commands;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.valueobjects.ServiceType;

import java.util.Date;

public record CreateNutritionistPatientCommand(
        Integer nutritionistId,
        Long patientUserId,
        ServiceType serviceType,
        Date startDate,
        Date scheduledAt
) {}
