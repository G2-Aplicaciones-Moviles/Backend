package pe.edu.upc.center.jameoFit.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.edu.upc.center.jameoFit.iam.application.internal.outboundservices.hashing.HashingService;
import pe.edu.upc.center.jameoFit.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.upc.center.jameoFit.iam.domain.model.aggregates.User;
import pe.edu.upc.center.jameoFit.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.center.jameoFit.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.center.jameoFit.iam.domain.model.entities.Role;
import pe.edu.upc.center.jameoFit.iam.domain.model.valueobjects.Roles;
import pe.edu.upc.center.jameoFit.iam.domain.services.UserCommandService;
import pe.edu.upc.center.jameoFit.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.edu.upc.center.jameoFit.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService,
                                  RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var userOpt = userRepository.findByUsername(command.username());
        if (userOpt.isEmpty())
            throw new RuntimeException("Profile not found");
        var user = userOpt.get();
        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.getUsername());
        return Optional.of(ImmutablePair.of(user, token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        // ✅ SOLUCIÓN: asociar roles existentes, no crear nuevos
        Set<Role> rolesToAssign = new HashSet<>();
        if (command.roles() != null && !command.roles().isEmpty()) {
            for (var roleVO : command.roles()) {
                var roleEntity = roleRepository.findByName(roleVO.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleVO.getName()));
                rolesToAssign.add(roleEntity);
            }
        } else {
            // Asignar ROLE_USER por defecto si no se envían roles
            var defaultRole = roleRepository.findByName(Roles.valueOf("ROLE_USER"))
                    .orElseThrow(() -> new RuntimeException("Default role ROLE_USER not found"));
            rolesToAssign.add(defaultRole);
        }

        var user = new User(command.username(), hashingService.encode(command.password()));
        user.setRoles(rolesToAssign);

        userRepository.save(user);

        return userRepository.findByUsername(command.username());
    }
}
