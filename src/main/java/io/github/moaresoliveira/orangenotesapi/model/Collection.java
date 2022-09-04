package io.github.moaresoliveira.orangenotesapi.model;

import io.github.moaresoliveira.orangenotesapi.enums.CollectionType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Collection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "parent",fetch = FetchType.LAZY)
    private List<Collection> content;
    @Enumerated(EnumType.STRING)
    private CollectionType type;
    @ManyToOne(targetEntity = Collection.class)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Collection parent;

    @Column(name = "parent_id")
    private Long parentId;

    private String url;

    private Integer percentage;
    private boolean finished;
    private String createdAt;
    private String finishedAt;

    public Collection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Collection> getContent() {
        return content;
    }

    public void setContent(List<Collection> content) {
        this.content = content;
    }

    public CollectionType getType() {
        return type;
    }

    public void setType(CollectionType type) {
        this.type = type;
    }

    public Collection getParent() {
        return parent;
    }

    public void setParent(Collection parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", content=" + (content == null? "null": content.size()) +
                ", type=" + type +
                ", parent=" + parent +
                ", parentId=" + parentId +
                ", url='" + url + '\'' +
                ", percentage=" + percentage +
                ", finished=" + finished +
                ", createdAt='" + createdAt + '\'' +
                ", finishedAt='" + finishedAt + '\'' +
                '}';
    }
}
