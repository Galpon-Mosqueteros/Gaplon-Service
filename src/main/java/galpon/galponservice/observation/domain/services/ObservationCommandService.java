package galpon.galponservice.observation.domain.services;

import galpon.galponservice.observation.domain.model.commands.CreateObservationCommand;
import galpon.galponservice.observation.domain.model.aggregates.Observation;
import galpon.galponservice.observation.domain.model.commands.UpdateObservationCommand;

import java.util.Optional;

public interface ObservationCommandService {
    Observation createObservation(CreateObservationCommand command);
    Optional<Observation> updateObservation(UpdateObservationCommand command);
    void deleteObservation(Long id);
}