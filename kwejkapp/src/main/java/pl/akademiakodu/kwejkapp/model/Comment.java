package pl.akademiakodu.kwejkapp.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by itml on 25.06.2017.
 */
@Table(name = "COMMENT")
@Entity
public class Comment extends AbstractPersistable<Long> {

    @Column(name = "NICKNAME", nullable = false)
    @NotNull
    @NotEmpty
    private String nickname;

    @Column(name = "TEXT", nullable = false)
    @NotNull
    @NotEmpty
    private String text;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEM_ID", referencedColumnName = "ID")
    private Mem mem;

    public Comment() {
    }

    @PrePersist
    public void onPersist() {
        this.setTimestamp(LocalDateTime.now());
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Mem getMem() {
        return mem;
    }

    public void setMem(Mem mem) {
        this.mem = mem;
    }
}
