package galpon.galponservice.observation.interfaces.rest;

import galpon.galponservice.observation.domain.model.commands.CreateObservationCommand;
import galpon.galponservice.observation.domain.services.ObservationCommandService;
import galpon.galponservice.observation.domain.model.commands.UpdateObservationCommand;
import galpon.galponservice.observation.infrastructure.persistence.jpa.ObservationRepository;
import galpon.galponservice.observation.interfaces.rest.resources.CreateObservationResource;
import galpon.galponservice.observation.interfaces.rest.resources.ObservationResource;
import galpon.galponservice.observation.interfaces.rest.resources.UpdateObservationResource;
import galpon.galponservice.observation.interfaces.rest.transform.ObservationResourceFromEntityAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    private final ObservationCommandService observationCommandService;
    private final ObservationRepository observationRepository;

    @Autowired
    public ObservationController(ObservationCommandService observationCommandService,
                                 ObservationRepository observationRepository) {
        this.observationCommandService = observationCommandService;
        this.observationRepository = observationRepository;
    }

    @PostMapping
    public ResponseEntity<ObservationResource> createObservation(@RequestBody CreateObservationResource resource) {
        var observation = observationCommandService.createObservation(new CreateObservationCommand(
                resource.aveId(), resource.nombre(), resource.descripcion()));

        return new ResponseEntity<>(ObservationResourceFromEntityAssembler.toResource(observation), CREATED);
    }

    @GetMapping("/ave/{aveId}")
    public ResponseEntity<List<ObservationResource>> getObservationsByAveId(@PathVariable Long aveId) {
        var observations = observationRepository.findByAveId(aveId);
        return ResponseEntity.ok(observations.stream()
                .map(ObservationResourceFromEntityAssembler::toResource)
                .toList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateObservation(@PathVariable Long id, @RequestBody UpdateObservationResource resource) {
        var updatedObservation = observationCommandService.updateObservation(
                new UpdateObservationCommand(id, resource.nombre(), resource.descripcion()));

        return updatedObservation.map(observation ->
                        ResponseEntity.ok(ObservationResourceFromEntityAssembler.toResource(observation)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteObservation(@PathVariable Long id) {
        observationCommandService.deleteObservation(id);
        return ResponseEntity.noContent().build();
    }
}
