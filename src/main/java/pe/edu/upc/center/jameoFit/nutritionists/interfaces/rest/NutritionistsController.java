package pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates.Nutritionist;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.queries.GetNutritionistByUserQuery;
import pe.edu.upc.center.jameoFit.nutritionists.domain.services.NutritionistCommandService;
import pe.edu.upc.center.jameoFit.nutritionists.domain.services.NutritionistQueryService;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.resources.*;
import pe.edu.upc.center.jameoFit.nutritionists.interfaces.rest.transform.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/nutritionists", produces = "application/json")
@Tag(name = "Nutritionists", description = "Nutritionists Management Endpoints")
public class NutritionistsController {

    private final NutritionistCommandService commandService;
    private final NutritionistQueryService queryService;

    public NutritionistsController(NutritionistCommandService commandService,
                                   NutritionistQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PutMapping("/profile")
    public ResponseEntity<NutritionistResource> registerOrUpdateProfile(
            @RequestParam Long userId,
            @RequestBody NutritionistProfileResource resource) {
        try {
            Nutritionist updated = commandService.handle(
                    RegisterOrUpdateProfileCommandFromResourceAssembler.toCommand(userId, resource)
            );
            var res = NutritionistResourceFromEntityAssembler.toResourceFromEntity(updated);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/availability")
    public ResponseEntity<NutritionistResource> updateAvailability(
            @RequestParam Long userId,
            @RequestBody AvailabilityConfigResource resource) {
        try {
            Nutritionist updated = commandService.handle(
                    UpdateAvailabilityCommandFromResourceAssembler.toCommand(userId, resource)
            );
            var res = NutritionistResourceFromEntityAssembler.toResourceFromEntity(updated);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<NutritionistResource> getByUser(@RequestParam Long userId) {
        try {
            Optional<Nutritionist> nutritionist = queryService.handle(new GetNutritionistByUserQuery(userId));
            return nutritionist
                    .map(NutritionistResourceFromEntityAssembler::toResourceFromEntity)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
