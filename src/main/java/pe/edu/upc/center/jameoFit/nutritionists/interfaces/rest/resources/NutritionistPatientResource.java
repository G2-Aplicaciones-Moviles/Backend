
package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources;

import pe.edu.upc.center.jameoFit.nutritionists.domain.model.valueobjects.ServiceType;

import java.util.Date;

public record NutritionistPatientResource(
        Integer id,
        Integer nutritionistId,
        Long patientUserId,
        ServiceType serviceType,
        Date startDate,
        Date scheduledAt,
        Boolean accepted,
        Date requestedAt
) {}