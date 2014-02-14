package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "comment")
@AttributeOverride(name = "id", column = @Column(name = "cm_id", unique = true, updatable = false))
public class Comment extends AbstractEntity<Long> {

    @ManyToOne
    @Valid @NotNull
    @JoinColumn(name = "cm_question_id")
    private Question question;

    @ManyToOne
    @Valid @NotNull
    @JoinColumn(name = "cm_user_id")
    private User user;

    @Lob @NotBlank @Size(min = 20, max = 1000)
    @Column(name = "cm_content")
    private String content;

    @Temporal(TIMESTAMP)
    @Column(name = "cm_created", updatable = false)
    private Date created;

    public Comment() {
        this.created = new Date();
    }

    public Comment(Question question, User user, String content) {
        this();
        this.question = question;
        this.user = user;
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
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

        Comment comment = (Comment) o;

        if (!content.equals(comment.content)) return false;
        if (!created.equals(comment.created)) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }
}
