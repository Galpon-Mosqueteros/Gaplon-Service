package galpon.galponservice.bird.domain.services;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.commands.CreateBirdCommand;
import galpon.galponservice.bird.domain.model.commands.DeleteBirdCommand;
import galpon.galponservice.bird.domain.model.commands.UpdateBirdCommand;

import java.util.Optional;

public interface BirdCommandService {
    Optional<Bird> handle(UpdateBirdCommand updateBirdCommand);
    Optional<Bird> handle(CreateBirdCommand createBirdCommand, Long id);
    void handle(DeleteBirdCommand deleteBirdCommand);
}
