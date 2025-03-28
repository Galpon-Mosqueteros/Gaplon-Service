package galpon.galponservice.bird.interfaces.rest;

import galpon.galponservice.bird.application.internal.commandservices.BirdCommandServiceImpl;
import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.commands.DeleteBirdCommand;
import galpon.galponservice.bird.domain.services.BirdCommandService;
import galpon.galponservice.bird.domain.services.BirdQueryService;
import galpon.galponservice.bird.interfaces.rest.resources.BirdResource;
import galpon.galponservice.bird.interfaces.rest.resources.CreateBirdResource;
import galpon.galponservice.bird.interfaces.rest.resources.UpdateBirdResource;
import galpon.galponservice.bird.interfaces.rest.transform.BirdResourceFromEntityAssembler;
import galpon.galponservice.bird.interfaces.rest.transform.CreateBirdCommandFromResourceAssembler;
import galpon.galponservice.bird.interfaces.rest.transform.UpdateBirdCommandFromResourceAssembler;
import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.iam.domain.model.valueobjects.Email;
import galpon.galponservice.iam.domain.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/birds")
public class BirdController {
    private final BirdCommandService birdCommandService;
    private final BirdQueryService birdQueryService;
    private final UserRepository userRepository;

    public BirdController(BirdCommandService birdCommandService, BirdQueryService birdQueryService, UserRepository userRepository) {
        this.birdCommandService = birdCommandService;
        this.birdQueryService = birdQueryService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Consultar aves",
            description = "Brinda una lista de los datos de las aves que tiene el usuario")
    @GetMapping
    public ResponseEntity<List<BirdResource>> getBirdsByUserId(Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByEmail(new Email(username));
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        Long userId = user.get().getId(); // Obtiene el ID del usuario
        List<Bird> birds = birdQueryService.getBirdsByUserId(userId);
        return ResponseEntity.ok(birds.stream().map(BirdResourceFromEntityAssembler::toResourceFromEntity).toList());
    }

    @Operation(summary = "Actualizar un ave",
            description = "Modifica los datos de un ave existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ave actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos de entrada"),
            @ApiResponse(responseCode = "404", description = "Usuario o ave no encontrada")
    })
    @PostMapping
    public ResponseEntity<?> createBird(@RequestBody CreateBirdResource resource, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByEmail(new Email(username));
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        Long userId = user.get().getId();
        try {
            Optional<Bird> bird = birdCommandService
                    .handle(CreateBirdCommandFromResourceAssembler.toCommandFromResource(resource), userId);

            if (bird.isPresent()) {
                BirdResource birdResource = BirdResourceFromEntityAssembler.toResourceFromEntity(bird.get());
                return ResponseEntity.status(CREATED).body(birdResource);
            } else {
                return ResponseEntity.status(400).body("No se pudo registrar el ave");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un ave",
            description = "Modifica los datos de un ave existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ave actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos de entrada"),
            @ApiResponse(responseCode = "404", description = "Usuario o ave no encontrada")
    })
    @PutMapping("{birdId}")
    public ResponseEntity<?> updateBird(@PathVariable Long birdId, @RequestBody UpdateBirdResource resource, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByEmail(new Email(username));
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        try {
            var updateBirdCommand = UpdateBirdCommandFromResourceAssembler.toCommandFromResource(birdId, resource);
            var updatedBird = birdCommandService.handle(updateBirdCommand);

            if (updatedBird.isPresent()) {
                BirdResource birdResource = BirdResourceFromEntityAssembler.toResourceFromEntity(updatedBird.get());
                return ResponseEntity.ok(birdResource);
            } else {
                return ResponseEntity.status(400).body("No se pudo actualizar el ave");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



    @Operation(summary = "Eliminar un ave por ID",
            description = "Elimina un ave usando su ID. Si el ave no existe, devuelve un error.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "El ave se eliminó correctamente"),
            @ApiResponse(responseCode = "404", description = "El ave no fue encontrada")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBird(@PathVariable Long id) {
        try {
            birdCommandService.handle(new DeleteBirdCommand(id));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("El ave con ID " + id + " no fue encontrada");
        }
    }
}
