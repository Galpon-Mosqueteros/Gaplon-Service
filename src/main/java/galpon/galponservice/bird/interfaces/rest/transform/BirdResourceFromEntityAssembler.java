package galpon.galponservice.bird.interfaces.rest.transform;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.interfaces.rest.resources.BirdResource;

public class BirdResourceFromEntityAssembler {
    public static BirdResource toResourceFromEntity(Bird bird) {
        return new BirdResource(bird.getId(), bird.getPlaca(), bird.getNombre(), bird.getTipo(), bird.getColor(),
                bird.getPeso(), bird.getEstado(), bird.getFechaNacimiento(), bird.getFechaMuerte());
    }
}
