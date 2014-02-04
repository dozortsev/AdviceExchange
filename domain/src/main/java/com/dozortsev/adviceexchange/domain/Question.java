package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "question")
@AttributeOverride(name = "id", column = @Column(name = "qs_id", unique = true, nullable = false))
public class Question extends AbstractEntity<Long> {

    @NotBlank @Size(min = 30, max = 200)
    @Column(name = "qs_name")
    private String name;

    @ManyToOne
    @Valid @NotNull
    @JoinColumn(name = "qs_user_id")
    private User user;

    @NotNull @Column(name = "qs_votes")
    private Integer votes = 0;

    @Temporal(TIMESTAMP)
    @Column(name = "qs_created", updatable = false)
    private Date created;

    @Lob @NotBlank @Size(min = 100)
    @Column(name = "qs_content")
    private StringBuilder content = new StringBuilder(1000);

    @Valid @Size(min = 1, max = 5)
    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "question_tag",
            joinColumns = @JoinColumn(name = "qt_question_id"),
            inverseJoinColumns = @JoinColumn(name = "qt_tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @Valid
    @OneToMany(fetch = LAZY, cascade = REMOVE, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @Valid
    @OneToMany(fetch = LAZY, cascade = REMOVE, mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();

    public Question() { }

    public Question(String name, User user, Integer votes, StringBuilder content, Integer views) {
        this.name = name;
        this.user = user;
        this.votes = votes;
        this.content = content;
    }

    public Question(String name, User user, Integer votes, StringBuilder content, Integer views, List<Tag> tags, List<Answer> answers, List<Comment> comments) {
        this.name = name;
        this.user = user;
        this.votes = votes;
        this.content = content;
        this.tags = tags;
        this.answers = answers;
        this.comments = comments;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
        this.votes = votes;
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

    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!created.equals(question.created)) return false;
        if (!name.equals(question.name)) return false;
        if (!votes.equals(question.votes)) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + votes.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }
}
