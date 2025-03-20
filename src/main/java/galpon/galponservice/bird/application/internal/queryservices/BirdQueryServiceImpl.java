package galpon.galponservice.bird.application.internal.queryservices;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.queries.GetBirdByPlacaQuery;
import galpon.galponservice.bird.domain.services.BirdQueryService;
import galpon.galponservice.bird.infrastructure.persistence.jpa.BirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BirdQueryServiceImpl implements BirdQueryService {
    private final BirdRepository birdRepository;

    @Override
    public Optional<Bird> handle(GetBirdByPlacaQuery query) {
        return birdRepository.findByPlaca(query.placa());
    }
}
