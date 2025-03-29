package galpon.galponservice.observation.domain.model.commands;

public record UpdateObservationCommand(Long id, String nombre, String descripcion) {
}
