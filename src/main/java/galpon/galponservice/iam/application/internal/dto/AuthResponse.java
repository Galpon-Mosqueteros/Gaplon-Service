package galpon.galponservice.iam.application.internal.dto;

public class AuthResponse {
    private String token;

    public AuthResponse() {}  // Constructor vacío

    public AuthResponse(String token) {  // Constructor con parámetro
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}