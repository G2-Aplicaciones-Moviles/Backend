package pe.edu.upc.center.jameoFit.nutritionists.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.center.jameoFit.nutritionists.domain.model.valueobjects.Specialty;
import pe.edu.upc.center.jameoFit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nutritionists", uniqueConstraints = {
        @UniqueConstraint(name = "uk_nutritionists_user", columnNames = "user_id")
})
public class Nutritionist extends AuditableAbstractAggregateRoot<Nutritionist> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId; // viene del IAM

    @NotBlank
    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @NotBlank
    @Column(name = "license_number", nullable = false, length = 45)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty", nullable = false)
    private Specialty specialty;

    @NotNull
    @Column(name = "years_experience", nullable = false)
    private Integer yearsExperience;

    @Column(name = "accepting_new_patients", nullable = false)
    private boolean acceptingNewPatients = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public Nutritionist(Long userId, String fullName, String licenseNumber,
                        Specialty specialty, Integer yearsExperience) {
        this.userId = userId;
        this.fullName = fullName;
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.yearsExperience = yearsExperience;
        this.acceptingNewPatients = true;
        this.updatedAt = new Date();
    }

    public void updateProfile(String fullName, String licenseNumber,
                              Specialty specialty, Integer yearsExperience) {
        this.fullName = fullName;
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.yearsExperience = yearsExperience;
        this.updatedAt = new Date();
    }

    public void updateAvailability(boolean acceptingNewPatients) {
        this.acceptingNewPatients = acceptingNewPatients;
        this.updatedAt = new Date();
    }
}
