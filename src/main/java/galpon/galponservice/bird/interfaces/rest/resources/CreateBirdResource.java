package galpon.galponservice.bird.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import galpon.galponservice.bird.domain.model.aggregates.EstadoAve;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;

import java.time.LocalDate;
import java.util.Date;

public record CreateBirdResource(String placa, String nombre, TipoAve tipo,
                                 String color, Double peso, EstadoAve estado,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate fechaNacimiento,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate fechaMuerte,
                                 String placaPadre, String placaMadre) {
    public CreateBirdResource {
        if (placa == null || placa.isBlank()) throw new IllegalArgumentException("Placa no puede ser nula o vacía");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre no puede ser nulo o vacío");
        if (tipo == null) throw new IllegalArgumentException("Tipo no puede ser nulo");
        if (color == null || color.isBlank()) throw new IllegalArgumentException("Color no puede ser nulo o vacío");
        if (peso == null) throw new IllegalArgumentException("Peso no puede ser nulo");
        if (estado == null) throw new IllegalArgumentException("Estado no puede ser nulo");
        if (fechaNacimiento == null) throw new IllegalArgumentException("Fecha de nacimiento no puede ser nula");
    }
}
