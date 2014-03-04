package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "comment")
@AttributeOverride(name = "id", column = @Column(name = "cm_id"))
public class Comment extends UserActivity {

    @Valid @NotNull
    @ManyToOne(cascade = { MERGE, PERSIST })
    private User user;

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = LAZY)
    @Valid @NotNull
    @JoinColumn(name = "cm_question_id")
    private Question question;

    public Comment() {
        super();
        setType(Type.COMMENT);
    }

    public Comment(User user, String content, Question question) {
        super(Type.COMMENT, content);
        this.user = user;
        this.question = question;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
}
