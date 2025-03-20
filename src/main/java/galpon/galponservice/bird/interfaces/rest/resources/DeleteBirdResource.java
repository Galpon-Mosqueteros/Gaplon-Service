package galpon.galponservice.bird.interfaces.rest.resources;

public record DeleteBirdResource(String placa) {
    public DeleteBirdResource {
        if (placa == null) {
            throw new IllegalArgumentException("placa is null");
        }
    }
}
