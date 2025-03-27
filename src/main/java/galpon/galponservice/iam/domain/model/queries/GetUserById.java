package galpon.galponservice.iam.domain.model.queries;

import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.iam.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class GetUserById {
    private final UserRepository userRepository;
    public GetUserById(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> execute(Long id) {
        return userRepository.findById(id);
    }
}
