package galpon.galponservice.bird.interfaces.rest.transform;

import galpon.galponservice.bird.domain.model.commands.CreateBirdCommand;
import galpon.galponservice.bird.interfaces.rest.resources.CreateBirdResource;

public class CreateBirdCommandFromResourceAssembler {
    public static CreateBirdCommand toCommandFromResource(CreateBirdResource resource) {
        return new CreateBirdCommand(
                resource.placa(), resource.nombre(), resource.tipo(),
                resource.color(), resource.peso(), resource.estado(),
                resource.fechaNacimiento(), resource.fechaMuerte(),
                resource.placaPadre(), resource.placaMadre()
        );
    }
}
