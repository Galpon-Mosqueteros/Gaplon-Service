package galpon.galponservice.observation.application.internal.queryservices;

import galpon.galponservice.observation.domain.model.aggregates.Observation;
import galpon.galponservice.observation.domain.services.ObservationQueryService;
import galpon.galponservice.observation.infrastructure.persistence.jpa.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationQueryServiceImpl implements ObservationQueryService {
    private final ObservationRepository observationRepository;

    @Autowired
    public ObservationQueryServiceImpl(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @Override
    public List<Observation> getObservationsByAveId(Long aveId) {
        return observationRepository.findByAveId(aveId);
    }
}
