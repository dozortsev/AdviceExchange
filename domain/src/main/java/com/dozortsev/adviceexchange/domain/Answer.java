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
    private int votes = 0;

    @Column(name = "asw_accepted")
    private boolean accept = Boolean.FALSE;

    public Answer() {
        super();
    }

    public Answer(User user, String content, int votes, Question question) {
        super(user, Type.ANSWER, content);
        this.votes = votes;
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getVotes() {
        return votes;
    }
    public void setVotes(int votes) {
        this.votes = votes;
    }

    public boolean isAccept() {
        return accept;
    }
    public void canAccept(boolean accept) {
        this.accept = accept;
    }

    public int changeVotes(int votes) {
        return this.votes += votes;
    }
}
