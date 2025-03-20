package galpon.galponservice.bird.application.internal.commandservices;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.commands.CreateBirdCommand;
import galpon.galponservice.bird.domain.services.BirdCommandService;
import galpon.galponservice.bird.infrastructure.persistence.jpa.BirdRepository;
import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.iam.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BirdCommandServiceImpl implements BirdCommandService {
    private final BirdRepository birdRepository;
    private final UserRepository userRepository;

    @Autowired
    public BirdCommandServiceImpl(BirdRepository birdRepository, UserRepository userRepository) {
        this.birdRepository = birdRepository;
        this.userRepository = userRepository;
    }

    //@Override
    //public Optional<Bird> handle(CreateBirdCommand command, Integer id) {
    //    User user = userRepository.findById(id).orElseThrow
    //}
}
