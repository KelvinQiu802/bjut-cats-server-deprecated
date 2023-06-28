package org.animalwatch.model;

public record CatImage(
        String openId,
        String imageUrl,
        ImageState state
) {
}
