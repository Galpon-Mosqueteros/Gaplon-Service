package galpon.galponservice.bird.application.internal.queryservices;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.queries.GetBirdByPlacaQuery;
import galpon.galponservice.bird.domain.services.BirdQueryService;
import galpon.galponservice.bird.infrastructure.persistence.jpa.BirdRepository;
import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.iam.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdQueryServiceImpl implements BirdQueryService {
    private final BirdRepository birdRepository;
    private final UserRepository userRepository;

    @Autowired
    public BirdQueryServiceImpl(BirdRepository birdRepository, UserRepository userRepository) {
        this.birdRepository = birdRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Bird> handle(GetBirdByPlacaQuery query) {
        return birdRepository.findByPlaca(query.placa());
    }

    @Override
    public List<Bird> getBirdsByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return birdRepository.findByUsuario_Id(user.getId());
    }

    @Override
    public Optional<Bird> getBirdById(Long id) {
        return birdRepository.findById(id);
    }

}
