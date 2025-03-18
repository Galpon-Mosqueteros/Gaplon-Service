package galpon.galponservice.iam.domain.model.aggregates;

import galpon.galponservice.iam.domain.model.valueobjects.Email;
import galpon.galponservice.iam.domain.model.valueobjects.Password;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "email", unique = true, nullable = false))
    })
    private Email email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "password", nullable = false))
    })
    private Password password;

    private String nombreGalpon;

    public User(String nombre, String apellido, Email email, Password password, String nombreGalpon) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.nombreGalpon = nombreGalpon;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }
}
