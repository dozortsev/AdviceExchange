package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

@Entity @Table(name = "comment")
@PrimaryKeyJoinColumn(name = "cm_id")
public class Comment extends UserActivity {

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = EAGER)
    @JoinColumn(name = "cm_question_id")
    private Question question;

    public Comment() {
        super();
    }

    public Comment(User user, String content, Question question) {
        super(user, Type.COMMENT, content);
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
}
