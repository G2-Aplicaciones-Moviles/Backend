package pe.edu.upc.center.jameoFit.profiles.interfaces.rest.resources;

import java.util.List;
public record UserProfileResource(
        int id,
        String gender,
        double height,
        double weight,
        int userScore,
        String birthDate,       // ✅ debe ser String, no Long
        Long activityLevelId,
        String activityLevelName,
        int objectiveId,
        String objectiveName,
        List<String> allergyNames
) {


    public int getId() {
        return id;
    }
}