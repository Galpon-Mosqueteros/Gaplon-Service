package galpon.galponservice.bird.domain.model.commands;

public record DeleteBirdCommand(String placa) {
    public DeleteBirdCommand {
        if (placa == null) {
            throw new NullPointerException("placa no puede ser null");
        }
    }
}
