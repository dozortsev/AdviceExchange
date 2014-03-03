package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.InheritanceType.JOINED;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "user_activity")
@Inheritance(strategy = JOINED)
@AttributeOverride(
        name = "id", column = @Column(name = "ua_id")
)
public class UserActivity extends AbstractEntity<Long> {

    @NotNull @Enumerated(STRING)
    @Column(name = "ua_type", updatable = false)
    private Type type;

    @Lob @NotBlank
    @Column(name = "ua_content")
    private String content;

    @Temporal(TIMESTAMP)
    @Column(name = "ua_created", updatable = false)
    private Date created;

    public UserActivity() {
        created = new Date();
    }

    public UserActivity(Type type, String content) {
        this();
        this.type = type;
        this.content = content;
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
