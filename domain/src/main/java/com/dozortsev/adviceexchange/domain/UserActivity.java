package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
public abstract class UserActivity extends AbstractEntity<Long> {

    @Lob @NotBlank
    private String content;

    @Temporal(TIMESTAMP)
    @Column(updatable = false)
    private Date created;

    public UserActivity() {
        created = new Date();
    }

    public UserActivity(String content) {
        this();
        this.content = content;
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
