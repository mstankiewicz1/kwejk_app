package pl.akademiakodu.kwejkapp.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by itml on 24.06.2017.
 */
@Table(name = "MEM")
@Entity
public class Mem extends AbstractPersistable<Long> {

    @Column(name = "TITLE", nullable = false)
    @NotEmpty(message = "Pole tytuł nie może być puste")
    @NotNull
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    @NotEmpty(message = "Pole opis nie może być puste")
    @NotNull
    private String description;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "IMAGE_PATH", unique = true, nullable = false)
    private String imagePath;

    @Column(name = "VISIT_COUNT")
    private int visitCount;

    @OneToMany(mappedBy = "mem")
    private List<Comment> comments;

    public Mem() {
    }

    @PrePersist
    public void onPersist() {
        this.setTimestamp(LocalDateTime.now());
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public Long getId() {
        return super.getId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void increaseVisitCount() {
        ++this.visitCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Mem{" +
                "id='" + getId() +'\'' +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", imagePath='" + imagePath + '\'' +
                ", visitCount=" + visitCount +
                '}';
    }
}
