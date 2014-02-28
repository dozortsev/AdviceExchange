package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity @Table(name = "comment")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "cm_id")),
        @AttributeOverride(name = "content", column = @Column(name = "cm_content")),
        @AttributeOverride(name = "created", column = @Column(name = "cm_created"))
})
public class Comment extends UserActivity {

    @ManyToOne(cascade = { MERGE, PERSIST })
    @Valid @NotNull
    @JoinColumn(name = "cm_question_id")
    private Question question;

    @ManyToOne(cascade = { MERGE, PERSIST })
    @Valid @NotNull
    @JoinColumn(name = "cm_user_id")
    private User user;

    public Comment() {
        super();
    }

    public Comment(Question question, User user, String content) {
        super(content);
        this.question = question;
        this.user = user;
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
}
