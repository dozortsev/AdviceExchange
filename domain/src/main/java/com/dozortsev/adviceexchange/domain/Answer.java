package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "answer")
public class Answer extends AbstractEntity<Long> {

    @Valid @NotNull
    @ManyToOne
    @JoinColumn(name = "asw_question_id")
    private Question question;

    @Valid @NotNull
    @ManyToOne
    @Column(name = "asw_user_id")
    private User user;

    @NotNull @Column(name = "asw_votes")
    private Integer votes = 0;

    @NotNull @Temporal(TIMESTAMP)
    @Column(name = "asw_created", updatable = false)
    private Date created;

    @Lob @NotEmpty @Min(50)
    @Column(name = "asw_content")
    private StringBuilder content = new StringBuilder(1000);

    @NotNull @AssertFalse
    @Column(name = "asw_accepted")
    private Boolean isAccepted;


    public Answer() { }

    public Answer(Question question, Integer votes, User user, StringBuilder content, Boolean isAccepted) {
        this.question = question;
        this.votes += votes;
        this.user = user;
        this.content = content;
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

    public Date getCreated() {
        return created;
    }
    @SuppressWarnings("unused")
    public void setCreated(Date created) {
        this.created = created;
    }

    public StringBuilder getContent() {
        return content;
    }
    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }
    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
