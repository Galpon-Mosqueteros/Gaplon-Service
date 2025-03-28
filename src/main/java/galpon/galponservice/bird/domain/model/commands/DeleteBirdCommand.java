package galpon.galponservice.bird.domain.model.commands;

public record DeleteBirdCommand(Long id) {
    public DeleteBirdCommand {
        if (id == null) {
            throw new NullPointerException("id no puede ser null");
        }
    }
}
