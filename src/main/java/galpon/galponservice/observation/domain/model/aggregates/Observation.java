package galpon.galponservice.observation.domain.model.aggregates;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ave_id", nullable = false)
    private Bird ave;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    public Observation(Long id, Bird ave, String nombre, String descripcion) {
        this.id = id;
        this.ave = ave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    public Observation() {}

    public Long getId() { return id; }
    public Bird getAve() { return ave; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFecha() { return fecha; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

}
