package org.animalwatch.model;

public record Cat(
        int id,
        String name,
        Campus campus,
        String avatar,
        String image,  // TODO: 等把image迁移到新的表就可以删掉
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
