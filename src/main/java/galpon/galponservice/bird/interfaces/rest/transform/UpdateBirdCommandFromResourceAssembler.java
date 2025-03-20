package galpon.galponservice.bird.interfaces.rest.transform;

import galpon.galponservice.bird.domain.model.commands.UpdateBirdCommand;
import galpon.galponservice.bird.interfaces.rest.resources.UpdateBirdResource;

public class UpdateBirdCommandFromResourceAssembler {
    public static UpdateBirdCommand toCommandFromResource(Integer id, UpdateBirdResource resource) {
        return new UpdateBirdCommand(
                id,
                resource.placa(),
                resource.nombre(),
                resource.tipo(),
                resource.color(),
                resource.peso(),
                resource.estado(),
                resource.fechaNacimiento(),
                resource.fechaMuerte()
        );
    }
}
