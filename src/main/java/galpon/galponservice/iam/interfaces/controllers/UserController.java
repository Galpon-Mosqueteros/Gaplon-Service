package galpon.galponservice.iam.interfaces.controllers;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.interfaces.rest.resources.BirdResource;
import galpon.galponservice.bird.interfaces.rest.transform.BirdResourceFromEntityAssembler;
import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.iam.domain.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}/birds")
    public ResponseEntity<List<BirdResource>> getBirdsByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        List<Bird> birds = user.get().getBirds();
        List<BirdResource> birdResources = birds.stream()
                .map(BirdResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(birdResources);
    }
}
