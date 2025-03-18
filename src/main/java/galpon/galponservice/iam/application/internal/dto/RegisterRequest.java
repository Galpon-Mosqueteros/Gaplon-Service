package galpon.galponservice.iam.application.internal.dto;


public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String nombreGalpon;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreGalpon() {
        return nombreGalpon;
    }
}
