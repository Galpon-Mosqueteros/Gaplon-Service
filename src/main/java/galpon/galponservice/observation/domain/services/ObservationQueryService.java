package galpon.galponservice.observation.domain.services;

import galpon.galponservice.observation.domain.model.aggregates.Observation;

import java.util.List;

public interface ObservationQueryService {
    List<Observation> getObservationsByAveId(Long aveId);
}
