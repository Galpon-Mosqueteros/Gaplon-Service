package galpon.galponservice.iam.application.internal.dto;

import galpon.galponservice.bird.interfaces.rest.resources.BirdResource;

import java.util.List;

public record UserResource(Long id, String email, String name, List<BirdResource> birds) {}
