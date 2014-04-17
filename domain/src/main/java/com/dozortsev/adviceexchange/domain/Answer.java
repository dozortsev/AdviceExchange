package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

@Entity @Table(name = "answer")
@PrimaryKeyJoinColumn(name = "asw_id")
public class Answer extends UserActivity {

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = EAGER)
    @JoinColumn(name = "asw_question_id")
    private Question question;

    @Column(name = "asw_votes")
    private Integer votes;

    @Column(name = "asw_accepted")
    private Boolean accept;

    public Answer() {
        super(Type.ANSWER);
        this.votes = 0;
        this.accept = Boolean.FALSE;
    }

    public Answer(User user, String content, Question question, Boolean accept) {
        super(user, Type.ANSWER, content);
        this.votes = 0;
        this.question = question;
        this.accept = accept;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getVotes() {
        return votes;
    }
    public void setVotes(Integer votes) {
        this.votes += votes;
    }

    public Boolean isAccept() {
        return accept;
    }
    public void canAccept(Boolean accept) {
        this.accept = accept;
    }
}
