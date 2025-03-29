package galpon.galponservice.observation.interfaces.rest.transform;

import galpon.galponservice.observation.domain.model.commands.UpdateObservationCommand;
import galpon.galponservice.observation.interfaces.rest.resources.UpdateObservationResource;

public class UpdateObservationCommandFromResourceAssembler {
    public static UpdateObservationCommand toCommand(Long id, UpdateObservationResource resource) {
        return new UpdateObservationCommand(id, resource.nombre(), resource.descripcion());
    }
}
