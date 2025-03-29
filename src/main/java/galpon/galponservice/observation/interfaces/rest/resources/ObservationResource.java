package galpon.galponservice.observation.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ObservationResource(
        Long id,
        Long aveId,
        String nombre,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fecha
) {}
