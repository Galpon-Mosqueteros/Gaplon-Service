package galpon.galponservice.bird.application.internal.commandservices;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.commands.CreateBirdCommand;
import galpon.galponservice.bird.domain.model.commands.DeleteBirdCommand;
import galpon.galponservice.bird.domain.model.commands.UpdateBirdCommand;
import galpon.galponservice.bird.domain.services.BirdCommandService;
import galpon.galponservice.bird.infrastructure.persistence.jpa.BirdRepository;
import galpon.galponservice.iam.domain.model.aggregates.User;
import galpon.galponservice.iam.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BirdCommandServiceImpl implements BirdCommandService {
    private final BirdRepository birdRepository;
    private final UserRepository userRepository;

    @Autowired
    public BirdCommandServiceImpl(BirdRepository birdRepository, UserRepository userRepository) {
        this.birdRepository = birdRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Bird> handle(CreateBirdCommand command, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Bird bird = new Bird(command, user, null, null);

        return Optional.of(birdRepository.save(bird));
    }

    @Override
    public Optional<Bird> handle(UpdateBirdCommand command) {
        validateUpdateBirdCommand(command);
        var result = birdRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("El ave no existe");
        }
        var birdToUpdate = result.get();
        try {
            var updatedBird = birdRepository.save(birdToUpdate.updateInformation(command.placa(), command.nombre(), command.tipo(),
                    command.color(), command.peso(), command.estado(), command.fechaNacimiento(), command.fechaMuerte()));
            return Optional.of(updatedBird);
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo actualizar el ave");
        }
    }

    void validateUpdateBirdCommand(UpdateBirdCommand command) {
        if (command.id() == null){
            throw new IllegalArgumentException("Id nulo");
        }
        if (command.color() == null){
            throw new IllegalArgumentException("Color nulo");
        }
        if (command.nombre() == null){
            throw new IllegalArgumentException("Nombre nulo");
        }
        if (command.estado() == null){
            throw new IllegalArgumentException("Estado nulo");
        }
        if (command.placa() == null){
            throw new IllegalArgumentException("Placa nulo");
        }
        if (command.tipo() == null){
            throw new IllegalArgumentException("Tipo nulo");
        }
        if (command.peso() == null){
            throw new IllegalArgumentException("Peso nulo");
        }
        if (command.fechaNacimiento() == null){
            throw new IllegalArgumentException("FechaNacimiento nulo");
        }
    }

    @Override
    public void handle(DeleteBirdCommand command) {
        Optional<Bird> bird = birdRepository.findByPlaca(command.placa());
        if (bird.isPresent()) {
            birdRepository.delete(bird.get());
        } else {
            throw new IllegalArgumentException("Ave no encontrada");
        }
    }

}
