package galpon.galponservice.bird.domain.model.commands;

import galpon.galponservice.bird.domain.model.aggregates.EstadoAve;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;

import java.util.Date;

public record CreateBirdCommand(String placa, String nombre, TipoAve tipo,
                                String color, Double peso, EstadoAve estado, Date fechaNacimiento,
                                Date fechaMuerte, String placaPadre, String placaMadre) {
    public CreateBirdCommand {
        if (placa == null || placa.isEmpty()) throw new IllegalArgumentException("Placa no puede ser nula");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser nulo");
        if (tipo == null) throw new IllegalArgumentException("Tipo no puede ser nulo");
        if (color == null || color.isEmpty()) throw new IllegalArgumentException("Color no puede ser nulo");
        if (peso == null || peso < 0) throw new IllegalArgumentException("Peso no puede ser nulo o negativo");
        if (estado == null) throw new IllegalArgumentException("Estado no puede ser nulo");
        if (fechaNacimiento == null) throw new IllegalArgumentException("Fecha nacimiento no puede ser nulo");
        if (fechaMuerte != null && fechaMuerte.before(fechaNacimiento))
            throw new IllegalArgumentException("Fecha de muerte no puede ser antes de la fecha de nacimiento");
    }
}
