package io.github.moaresoliveira.orangenotesapi.model.form;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CollectionForm {

    private String name;
    private String description;
    private String url;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CollectionForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
