package org.animalwatch.model;

public record User(
        String openId,
        String userName,
        Role role
) {
}
