package galpon.galponservice.observation.interfaces.rest.transform;

import galpon.galponservice.observation.domain.model.aggregates.Observation;
import galpon.galponservice.observation.interfaces.rest.resources.ObservationResource;

public class ObservationResourceFromEntityAssembler {
    public static ObservationResource toResource(Observation observation) {
        return new ObservationResource(
                observation.getId(),
                observation.getAve().getId(),
                observation.getNombre(),
                observation.getDescripcion(),
                observation.getFecha()
        );
    }
}
