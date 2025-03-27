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

    @PostMapping
    public ResponseEntity<BirdResource> createBird(@RequestBody CreateBirdResource resource, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByEmail(new Email(username));
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        Long userId = user.get().getId();
        Optional<Bird> bird = birdCommandService
                .handle(CreateBirdCommandFromResourceAssembler.toCommandFromResource(resource), userId);

        return bird.map(source -> new ResponseEntity<>(BirdResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<BirdResource>> getBirdsByUserId(Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByEmail(new Email(username));
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        Long userId = user.get().getId(); // Obtiene el ID del usuario
        List<Bird> birds = birdQueryService.getBirdsByUserId(userId);
        return ResponseEntity.ok(birds.stream().map(BirdResourceFromEntityAssembler::toResourceFromEntity).toList());
    }


    @PutMapping("{birdId}")
    public ResponseEntity<BirdResource> updateBird(@PathVariable Long birdId, @RequestBody UpdateBirdResource resource, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByEmail(new Email(username));
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        var updateBirdCommand = UpdateBirdCommandFromResourceAssembler.toCommandFromResource(birdId, resource);
        var updatedBird = birdCommandService.handle(updateBirdCommand);

        if (updatedBird.isEmpty()) return ResponseEntity.badRequest().build();

        var birdResource = BirdResourceFromEntityAssembler.toResourceFromEntity(updatedBird.get());
        return ResponseEntity.ok(birdResource);
    }

    @DeleteMapping("{placa}")
    public ResponseEntity<Void> deleteBird(@PathVariable String placa) {
        try {
            birdCommandService.handle(new DeleteBirdCommand(placa));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException a) {
            return ResponseEntity.notFound().build();
        }
    }
}
