package galpon.galponservice.bird.interfaces.rest.resources;

public record DeleteBirdResource(Long id) {
    public DeleteBirdResource {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
    }
}
