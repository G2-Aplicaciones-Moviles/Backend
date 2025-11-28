package pe.edu.upc.center.jameoFit.iam.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.iam.domain.model.commands.SeedRolesCommand;
import pe.edu.upc.center.jameoFit.iam.domain.services.RoleCommandService;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ApplicationReadyEventHandler class
 * This class is used to handle the ApplicationReadyEvent
 */
@Service
public class ApplicationReadyEventHandler {
    private final RoleCommandService roleCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    // Bandera para asegurar que el seeding solo se ejecute una vez
    private final AtomicBoolean rolesSeeded = new AtomicBoolean(false);

    public ApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the roles
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        // Verificar si ya se ejecutó el seeding
        if (!rolesSeeded.compareAndSet(false, true)) {
            LOGGER.info("Roles seeding already executed, skipping...");
            return;
        }

        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if roles seeding is needed for {} at {}",
                applicationName, currentTimestamp());

        var seedRolesCommand = new SeedRolesCommand();
        roleCommandService.handle(seedRolesCommand);

        LOGGER.info("Roles seeding verification finished for {} at {}",
                applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}