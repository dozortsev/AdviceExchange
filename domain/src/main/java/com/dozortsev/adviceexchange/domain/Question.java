package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "question")
@AttributeOverride(
        name = "id", column = @Column(name = "qs_id")
)
public class Question extends UserActivity {

    @NotBlank @Size(min = 10, max = 40)
    @Column(name = "qs_name")
    private String name;

    @NotNull @Column(name = "qs_votes")
    private Integer votes = 0;

    @Valid @Size(min = 1, max = 5)
    @ManyToMany
    @JoinTable(name = "question_tag",
            joinColumns = @JoinColumn(name = "qt_question_id"),
            inverseJoinColumns = @JoinColumn(name = "qt_tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @Valid
    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @Valid
    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();

    public Question() {
        super();
        setType(Type.QUESTION);
    }

    public Question(User user, String content, String name, Integer votes) {
        super(Type.QUESTION, user, content);
        this.name = name;
        this.votes = votes;
    }

    public Question(User user, String content, String name, Integer votes, List<Tag> tags, List<Answer> answers, List<Comment> comments) {
        this(user, content, name, votes);
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

    public Integer getVotes() {
        return votes;
    }
    public void setVotes(Integer votes) {
        this.votes = votes;
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
        if (!super.equals(o)) return false;

        Question question = (Question) o;

        if (!name.equals(question.name)) return false;
        if (!votes.equals(question.votes)) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + votes.hashCode();
        return result;
    }
}
