package galpon.galponservice.bird.interfaces.rest.transform;

import galpon.galponservice.bird.domain.model.commands.DeleteBirdCommand;
import galpon.galponservice.bird.interfaces.rest.resources.DeleteBirdResource;

public class DeleteBirdCommandFromResourceAssembler {
    public static DeleteBirdCommand toCommandFromResource(DeleteBirdResource resource) {
        return new DeleteBirdCommand(resource.id());
    }
}
