package galpon.galponservice.iam.application.internal.commandservices;

import galpon.galponservice.iam.infrastructure.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String createToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public boolean isValidToken(String token) {
        return jwtUtil.validateToken(token, getUsernameFromToken(token));
    }

    public String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }
}
