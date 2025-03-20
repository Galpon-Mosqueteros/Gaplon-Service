package galpon.galponservice.bird.interfaces.rest.resources;

import galpon.galponservice.bird.domain.model.aggregates.EstadoAve;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;

import java.util.Date;

public record BirdResource(Integer id, String placa, String nombre, TipoAve tipo,
                           String color, Double peso, EstadoAve estado, Date fechaNacimiento, Date fechaMuerte) {
}
