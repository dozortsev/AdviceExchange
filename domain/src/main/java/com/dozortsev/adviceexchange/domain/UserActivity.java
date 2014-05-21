package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.InheritanceType.JOINED;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "user_activity")
@Inheritance(strategy = JOINED)
@AttributeOverride(
        name = "id", column = @Column(name = "ua_id")
)
public abstract class UserActivity extends AbstractEntity<Long> {

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = EAGER)
    @JoinColumn(name = "ua_user_id")
    private User user;

    @Enumerated(STRING)
    @Column(name = "ua_type", updatable = false)
    private Type type;

    @Lob @Column(name = "ua_content")
    private String content;

    @Column(name = "ua_active", nullable = false)
    private boolean active = Boolean.TRUE;

    @Temporal(TIMESTAMP)
    @Column(name = "ua_created", updatable = false)
    private Date created = new Date();

    public UserActivity() {
    }

    public UserActivity(User user, Type type, String content) {
        this.type = type;
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActive() {
        return active;
    }
    public void canActive(boolean active) {
        this.active = active;
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

        if (active != that.active) return false;
        if (!created.equals(that.created)) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + created.hashCode();
        return result;
    }
}
