package galpon.galponservice.observation.interfaces.rest.transform;

import galpon.galponservice.observation.domain.model.commands.CreateObservationCommand;
import galpon.galponservice.observation.interfaces.rest.resources.CreateObservationResource;

public class CreateObservationCommandFromResourceAssembler {
    public static CreateObservationCommand toCommand(CreateObservationResource resource) {
        return new CreateObservationCommand(resource.aveId(), resource.nombre(), resource.descripcion());
    }
}
