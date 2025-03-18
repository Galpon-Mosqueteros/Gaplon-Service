package galpon.galponservice.iam.application.internal.commandservices;

import galpon.galponservice.iam.application.internal.dto.AuthResponse;
import galpon.galponservice.iam.application.internal.dto.LoginRequest;
import galpon.galponservice.iam.application.internal.dto.RegisterRequest;
import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.iam.domain.model.valueobjects.Email;
import galpon.galponservice.iam.domain.model.valueobjects.Password;
import galpon.galponservice.iam.domain.repositories.UserRepository;
import galpon.galponservice.iam.infrastructure.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse register(RegisterRequest request) {
        Email email = new Email(request.getEmail());

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User(
                request.getNombre(),
                request.getApellido(),
                email,
                new Password(passwordEncoder.encode(request.getPassword())),
                request.getNombreGalpon()
        );

        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthResponse(token);
    }
}
