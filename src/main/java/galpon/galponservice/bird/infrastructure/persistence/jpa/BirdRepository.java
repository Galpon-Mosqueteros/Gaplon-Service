package galpon.galponservice.bird.infrastructure.persistence.jpa;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long> {
    boolean existByPlaca(String placa);

    List<Bird> findByUser(User user);
}
