package galpon.galponservice.bird.interfaces.rest.resources;

import galpon.galponservice.bird.domain.model.aggregates.EstadoAve;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;

import java.time.LocalDate;
import java.util.Date;

public record UpdateBirdResource(String placa, String nombre, TipoAve tipo,
                                 String color, Double peso, EstadoAve estado,
                                 LocalDate fechaNacimiento, LocalDate fechaMuerte,
                                 String placaPadre, String placaMadre) {
}