package galpon.galponservice.bird.domain.model.queries;

public record GetBirdByPlacaQuery(String placa) {
    public GetBirdByPlacaQuery {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid placa");
        }
    }
}
