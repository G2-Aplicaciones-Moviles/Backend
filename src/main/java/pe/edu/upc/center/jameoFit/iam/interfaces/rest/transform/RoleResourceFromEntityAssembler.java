package pe.edu.upc.center.jameoFit.iam.interfaces.rest.transform;

import pe.edu.upc.center.jameoFit.iam.domain.model.entities.Role;
import pe.edu.upc.center.jameoFit.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
