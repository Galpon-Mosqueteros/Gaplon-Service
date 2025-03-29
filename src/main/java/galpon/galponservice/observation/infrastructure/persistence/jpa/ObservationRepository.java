package galpon.galponservice.observation.infrastructure.persistence.jpa;

import galpon.galponservice.observation.domain.model.aggregates.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    List<Observation> findByAveId(Long aveId);
}