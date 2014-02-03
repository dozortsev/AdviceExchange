package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "comment")
@AttributeOverride(name = "id", column = @Column(name = "cm_id", unique = true, nullable = false))
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
    private StringBuilder content = new StringBuilder(1000);

    @Temporal(TIMESTAMP)
    @Column(name = "cm_created", updatable = false)
    private Date created;


    public Comment() { }

    public Comment(Question question, User user, StringBuilder content) {
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

    public StringBuilder getContent() {
        return content;
    }
    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }
    @SuppressWarnings("unused")
    public void setCreated(Date created) {
        this.created = created;
    }
}
