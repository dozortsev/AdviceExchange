package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

@Entity @Table(name = "answer")
@PrimaryKeyJoinColumn(name = "asw_id")
public class Answer extends UserActivity {

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = EAGER)
    @JoinColumn(name = "asw_question_id")
    private Question question;

    @Column(name = "asw_accepted")
    private boolean accept = false;

    public Answer() {
        super();
    }

    public Answer(User user, String content, Question question) {
        super(user, Type.ANSWER, content);
        this.question = question;
    }

    public Answer(User user, String content, List<Vote> votes, Question question) {
        super(user, Type.ANSWER, content, votes);
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isAccept() {
        return accept;
    }
    public void canAccept(boolean accept) {
        this.accept = accept;
    }
}
