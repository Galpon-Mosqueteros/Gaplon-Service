package galpon.galponservice.bird.application.internal.commandservices;

import galpon.galponservice.bird.domain.model.aggregates.Bird;
import galpon.galponservice.bird.domain.model.aggregates.TipoAve;
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

        Bird padre = null, madre = null;

        if (command.placaPadre() != null) {
            padre = birdRepository.findByPlaca(command.placaPadre())
                    .filter(b -> b.getUsuario().equals(id))
                    .orElseThrow(() -> new IllegalArgumentException("El padre no pertenece al usuario o no existe"));
            if (padre.getTipo() != TipoAve.Gallo) throw new IllegalArgumentException("El padre debe ser un Gallo");
        }

        if (command.placaMadre() != null) {
            madre = birdRepository.findByPlaca(command.placaMadre())
                    .filter(b -> b.getUsuario().equals(id))
                    .orElseThrow(() -> new IllegalArgumentException("La madre no pertenece al usuario o no existe"));
            if (madre.getTipo() != TipoAve.Gallina) throw new IllegalArgumentException("La madre debe ser una Gallina");
        }

        Bird bird = new Bird(command, user, padre, madre);
        return Optional.of(birdRepository.save(bird));
    }

    @Override
    public Optional<Bird> handle(UpdateBirdCommand command) {
        validateUpdateBirdCommand(command);

        Bird birdToUpdate = birdRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("El ave no existe"));

        Bird padre = null, madre = null;

        if (command.placaPadre() != null) {
            padre = birdRepository.findByPlaca(command.placaPadre())
                    .filter(b -> b.getUsuario().equals(birdToUpdate.getUsuario()))
                    .orElseThrow(() -> new IllegalArgumentException("El padre no pertenece al usuario o no existe"));
            if (padre.getTipo() != TipoAve.Gallo) throw new IllegalArgumentException("El padre debe ser un Gallo");
        }

        if (command.placaMadre() != null) {
            madre = birdRepository.findByPlaca(command.placaMadre())
                    .filter(b -> b.getUsuario().equals(birdToUpdate.getUsuario()))
                    .orElseThrow(() -> new IllegalArgumentException("La madre no pertenece al usuario o no existe"));
            if (madre.getTipo() != TipoAve.Gallina) throw new IllegalArgumentException("La madre debe ser una Gallina");
        }

        var updatedBird = birdToUpdate.updateInformation(command.placa(), command.nombre(), command.tipo(),
                command.color(), command.peso(), command.estado(), command.fechaNacimiento(), command.fechaMuerte());

        updatedBird.setPadre(padre);
        updatedBird.setMadre(madre);

        return Optional.of(birdRepository.save(updatedBird));
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
        Optional<Bird> bird = birdRepository.findById(command.id());
        if (bird.isPresent()) {
            birdRepository.delete(bird.get());
        } else {
            throw new IllegalArgumentException("Ave no encontrada");
        }
    }

}
