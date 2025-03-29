package galpon.galponservice.observation.application.internal.commandservices;

import galpon.galponservice.bird.infrastructure.persistence.jpa.BirdRepository;
import galpon.galponservice.observation.domain.model.commands.CreateObservationCommand;
import galpon.galponservice.observation.domain.model.aggregates.Observation;
import galpon.galponservice.observation.domain.services.ObservationCommandService;
import galpon.galponservice.observation.domain.model.commands.UpdateObservationCommand;
import galpon.galponservice.observation.infrastructure.persistence.jpa.ObservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ObservationCommandServiceImpl implements ObservationCommandService {
    private final ObservationRepository observationRepository;
    private final BirdRepository birdRepository;

    @Autowired
    public ObservationCommandServiceImpl(ObservationRepository observationRepository, BirdRepository birdRepository) {
        this.observationRepository = observationRepository;
        this.birdRepository = birdRepository;
    }

    @Override
    @Transactional
    public Observation createObservation(CreateObservationCommand command) {
        var bird = birdRepository.findById(command.aveId())
                .orElseThrow(() -> new IllegalArgumentException("Ave no encontrada"));

        var observation = new Observation(null, bird, command.nombre(), command.descripcion());
        return observationRepository.save(observation);
    }

    @Override
    @Transactional
    public Optional<Observation> updateObservation(UpdateObservationCommand command) {
        return observationRepository.findById(command.id()).map(observation -> {
            observation.setNombre(command.nombre());
            observation.setDescripcion(command.descripcion());
            return observationRepository.save(observation);
        });
    }

    @Override
    @Transactional
    public void deleteObservation(Long id) {
        if (!observationRepository.existsById(id)) {
            throw new IllegalArgumentException("Observación no encontrada");
        }
        observationRepository.deleteById(id);
    }
}