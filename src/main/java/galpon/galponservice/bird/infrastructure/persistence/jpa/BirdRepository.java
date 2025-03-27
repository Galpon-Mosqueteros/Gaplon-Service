package galpon.galponservice.bird.infrastructure.persistence.jpa;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long> {
    boolean existsByPlaca(String placa);

    List<Bird> findByUsuario_Id(Long id);

    Optional<Bird> findByPlaca(String placa);
}
