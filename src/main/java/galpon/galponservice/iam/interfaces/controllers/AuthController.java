package galpon.galponservice.iam.interfaces.controllers;

import galpon.galponservice.iam.application.internal.commandservices.AuthService;
import galpon.galponservice.iam.application.internal.dto.AuthResponse;
import galpon.galponservice.iam.application.internal.dto.LoginRequest;
import galpon.galponservice.iam.application.internal.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    // Constructor manual
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
