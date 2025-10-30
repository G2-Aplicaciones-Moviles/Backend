package pe.edu.upc.center.jameoFit.mealplan.application.internal.commandservices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.mealplan.application.internal.outboundservices.acl.ExternalMealPlanRecipeService;
import pe.edu.upc.center.jameoFit.mealplan.domain.model.commands.CreateMealPlanEntryCommand;
import pe.edu.upc.center.jameoFit.mealplan.domain.model.entities.MealPlanEntry;
import pe.edu.upc.center.jameoFit.mealplan.domain.model.valueobjects.MealPlanTypes;
import pe.edu.upc.center.jameoFit.mealplan.domain.model.valueobjects.RecipeId;
import pe.edu.upc.center.jameoFit.mealplan.domain.services.MealPlanEntryCommandService;
import pe.edu.upc.center.jameoFit.mealplan.infrastructure.persistence.jpa.repositories.MealPlanEntryRepository;
import pe.edu.upc.center.jameoFit.mealplan.infrastructure.persistence.jpa.repositories.MealPlanRepository;
import pe.edu.upc.center.jameoFit.mealplan.infrastructure.persistence.jpa.repositories.MealPlanTypeRepository;

@Service
public class MealPlanEntryCommandServiceImpl implements MealPlanEntryCommandService {

    private final MealPlanRepository mealPlanRepository;
    private final MealPlanTypeRepository mealPlanTypeRepository;
    private final ExternalMealPlanRecipeService externalMealPlanRecipeService;

    public MealPlanEntryCommandServiceImpl(MealPlanRepository mealPlanRepository,
                                           MealPlanTypeRepository mealPlanTypeRepository,
                                           ExternalMealPlanRecipeService externalMealPlanRecipeService) {
        this.mealPlanRepository = mealPlanRepository;
        this.mealPlanTypeRepository = mealPlanTypeRepository;
        this.externalMealPlanRecipeService = externalMealPlanRecipeService;
    }

    @Override
    @Transactional
    public int handle(CreateMealPlanEntryCommand command) {
        // 1) Cargar el plan
        var plan = mealPlanRepository.findById(command.mealPlanId())
                .orElseThrow(() -> new IllegalArgumentException("MealPlan not found"));

        // 2) Validar tipo & día
        var mealTypeEnum = MealPlanTypes.valueOf(command.type());
        if (command.day() < 1 || command.day() > 7)
            throw new IllegalArgumentException("day must be between 1 and 7");

        var mealType = mealPlanTypeRepository.findByType(mealTypeEnum)
                .orElseThrow(() -> new IllegalArgumentException("MealPlanType not seeded: " + command.type()));

        // 3) Validar receta y obtener macros vía ACL
        externalMealPlanRecipeService.fetchRecipeById(command.recipeId())
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));

        var nutrition = externalMealPlanRecipeService.fetchNutrition(command.recipeId());

        // 4) Crear entry y agregar al agregado
        var entry = new MealPlanEntry(new RecipeId(command.recipeId()), mealType, command.day(), plan);
        plan.addEntry(entry);

        // 5) Actualizar totales del plan
        plan.addNutrition(nutrition.calories(), nutrition.carbs(), nutrition.proteins(), nutrition.fats());

        // 6) Persistir el agregado (cascade salva entries)
        mealPlanRepository.save(plan);

        return entry.getId();
    }
}

