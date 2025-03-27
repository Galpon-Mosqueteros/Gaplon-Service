package galpon.galponservice.bird.domain.services;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.queries.GetBirdByPlacaQuery;

import java.util.List;
import java.util.Optional;

public interface BirdQueryService {
    Optional<Bird> handle(GetBirdByPlacaQuery query);
    List<Bird> getBirdsByUserId(Long userId);
    Optional<Bird> getBirdById(Long id);
}
