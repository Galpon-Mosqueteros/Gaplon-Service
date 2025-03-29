package galpon.galponservice.bird.interfaces.rest.transform;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.interfaces.rest.resources.BirdResource;
import galpon.galponservice.observation.interfaces.rest.transform.ObservationResourceFromEntityAssembler;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BirdResourceFromEntityAssembler {
    public static BirdResource toResourceFromEntity(Bird bird) {
        return new BirdResource(
                bird.getId(), bird.getPlaca(), bird.getNombre(), bird.getTipo(),
                bird.getColor(), bird.getPeso(), bird.getEstado(),
                bird.getFechaNacimiento(), bird.getFechaMuerte(),
                bird.getPadre() != null ? bird.getPadre().getPlaca() : null,
                bird.getMadre() != null ? bird.getMadre().getPlaca() : null,
                bird.getObservations() != null
                        ? bird.getObservations().stream()
                        .map(ObservationResourceFromEntityAssembler::toResource)
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }
}
