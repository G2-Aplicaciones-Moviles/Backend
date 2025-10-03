package pe.edu.upc.center.jameoFit;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing
public class BackendJameoFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendJameoFitApplication.class, args);
	}

}
