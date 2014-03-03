package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.InheritanceType.JOINED;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table
@Inheritance(strategy = JOINED)
public class UserActivity extends AbstractEntity<Long> {

    private Type type;

    @Valid @NotNull
    @ManyToOne(cascade = { MERGE, PERSIST })
    @JoinColumn
    private User user;

    @Lob @NotBlank
    private String content;

    @Temporal(TIMESTAMP)
    @Column(updatable = false)
    private Date created;

    public UserActivity() {
        created = new Date();
    }

    public UserActivity(Type type, User user, String content) {
        this();
        this.type = type;
        this.user = user;
        this.content = content;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }
    @SuppressWarnings("unused")
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserActivity that = (UserActivity) o;

        if (!content.equals(that.content)) return false;
        if (!created.equals(that.created)) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }
}
