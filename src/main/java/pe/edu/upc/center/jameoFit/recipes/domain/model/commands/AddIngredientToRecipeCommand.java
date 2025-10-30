package pe.edu.upc.center.jameoFit.recipes.domain.model.commands;

// recipes.domain.model.commands
public record AddIngredientToRecipeCommand(int recipeId, int ingredientId, double amountGrams) { }
