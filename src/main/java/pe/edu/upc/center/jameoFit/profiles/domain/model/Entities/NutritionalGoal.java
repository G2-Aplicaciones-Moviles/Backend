//package pe.edu.upc.center.jameoFit.profiles.domain.model.Entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
///**
// * Representa las metas nutricionales de un usuario.
// */
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "nutritional_goals")
//public class NutritionalGoal {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Double dailyCalories;
//    private Double protein;
//    private Double carbs;
//    private Double fats;
//
//    public NutritionalGoal(Double dailyCalories, Double protein, Double carbs, Double fats) {
//        this.dailyCalories = dailyCalories;
//        this.protein = protein;
//        this.carbs = carbs;
//        this.fats = fats;
//    }
//}
