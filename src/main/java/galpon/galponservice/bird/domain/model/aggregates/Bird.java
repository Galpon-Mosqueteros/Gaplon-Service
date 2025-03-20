package galpon.galponservice.bird.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column
    private String placa;

    @NotBlank
    @Column
    private String nombre;

    @NotBlank
    @Column
    private TipoAve tipo;

    @NotBlank
    @Column
    private String color;

    @NotBlank
    @Column
    private Double peso;

    @NotBlank
    @Column
    private EstadoAve estado;

    @NotBlank
    @Column
    private Date fechaNacimiento;

    @NotBlank
    @Column
    private Date fechaMuerte;

    public Bird(String placa, String nombre, TipoAve tipo,
                String color, Double peso, EstadoAve estado, Date fechaNacimiento, Date fechaMuerte) {
        this.placa = placa;
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.peso = peso;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
    }

    public Bird() {}
}
