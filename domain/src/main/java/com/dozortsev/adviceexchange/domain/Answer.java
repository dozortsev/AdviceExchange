package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "answer")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "asw_id")),
        @AttributeOverride(name = "content", column = @Column(name = "asw_content")),
        @AttributeOverride(name = "created", column = @Column(name = "asw_created"))
})
public class Answer extends UserActivity {

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = LAZY)
    @Valid @NotNull
    @JoinColumn(name = "asw_question_id")
    private Question question;

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = LAZY)
    @Valid @NotNull
    @JoinColumn(name = "asw_user_id")
    private User user;

    @NotNull @Column(name = "asw_votes")
    private Integer votes = 0;

    @NotNull
    @Column(name = "asw_accepted")
    private Boolean isAccepted;

    public Answer() {
        super();
    }

    public Answer(Question question, Integer votes, User user, String content, Boolean isAccepted) {
        super(content);
        this.question = question;
        this.votes += votes;
        this.user = user;
        this.isAccepted = isAccepted;
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

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Answer answer = (Answer) o;

        if (!isAccepted.equals(answer.isAccepted)) return false;
        if (!votes.equals(answer.votes)) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + votes.hashCode();
        result = 31 * result + isAccepted.hashCode();
        return result;
    }
}
