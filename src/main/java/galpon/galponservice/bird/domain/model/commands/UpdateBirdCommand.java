package galpon.galponservice.bird.domain.model.commands;

import galpon.galponservice.bird.domain.model.aggregates.EstadoAve;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;

import java.util.Date;

public record UpdateBirdCommand(Integer id, String placa, String nombre, TipoAve tipo,
                                String color, Double peso, EstadoAve estado, Date fechaNacimiento, Date fechaMuerte) {}
