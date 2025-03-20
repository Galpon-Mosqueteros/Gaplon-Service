package galpon.galponservice.bird.interfaces.rest.resources;

import galpon.galponservice.bird.domain.model.aggregates.EstadoAve;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;

import java.util.Date;

public record CreateBirdResource(String placa, String nombre, TipoAve tipo,
                                 String color, Double peso, EstadoAve estado, Date fechaNacimiento, Date fechaMuerte) {
    public CreateBirdResource {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa nula");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre nula");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo nula");
        }
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Color nula");
        }
        if (peso == null) {
            throw new IllegalArgumentException("Peso nula");
        }
        if (estado == null) {
            throw new IllegalArgumentException("Estado nula");
        }
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("Fecha nula");
        }
        if (fechaMuerte == null) {
            throw new IllegalArgumentException("Fecha muerte nula");
        }
    }
}
