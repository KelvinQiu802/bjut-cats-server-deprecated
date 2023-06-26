package org.animalwatch.model;

public record Cat(
        int id,
        String name,
        Campus campus,
        String avatar,
        Gender gender,
        String color,
        String hair,
        Neutered neutered,
        CatState state,
        String description,
        String birthday,
        String adoptionDay,
        String position,
        double longitude,
        double latitude,
        int orderWeight
) {
}
