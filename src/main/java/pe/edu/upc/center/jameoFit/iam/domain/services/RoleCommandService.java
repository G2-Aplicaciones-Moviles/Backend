package pe.edu.upc.center.jameoFit.iam.domain.services;

import pe.edu.upc.center.jameoFit.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
