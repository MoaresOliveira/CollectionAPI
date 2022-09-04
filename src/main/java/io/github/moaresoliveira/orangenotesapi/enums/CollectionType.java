package io.github.moaresoliveira.orangenotesapi.enums;

public enum CollectionType {
    LINK("Link"),
    VIDEO("Video"),
    IMAGE("Image"),
    AUDIO("Audio"),
    COLLECTION("Collection"),
    CONTENT("Content");

    CollectionType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
