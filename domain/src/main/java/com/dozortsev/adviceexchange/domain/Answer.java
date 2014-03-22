package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "answer")
@PrimaryKeyJoinColumn(name = "asw_id")
public class Answer extends UserActivity {

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = LAZY)
    @JoinColumn(name = "asw_question_id")
    private Question question;

    @Column(name = "asw_votes")
    private Integer votes = 0;

    @Column(name = "asw_accepted")
    private Boolean isAccepted;

    public Answer() {
        super();
        setType(Type.ANSWER);
    }

    public Answer(User user, String content, Question question, Boolean isAccepted) {
        super(user, Type.ANSWER, content);
        this.question = question;
        this.isAccepted = isAccepted;
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

    public Boolean getIsAccepted() {
        return isAccepted;
    }
    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
