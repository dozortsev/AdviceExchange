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
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "qs_id")),
        @AttributeOverride(name = "content", column = @Column(name = "qs_content")),
        @AttributeOverride(name = "created", column = @Column(name = "qs_created"))
})
public class Question extends UserActivity {

    @NotBlank @Size(min = 10, max = 200)
    @Column(name = "qs_name")
    private String name;

    @ManyToOne(cascade = { MERGE, PERSIST }, fetch = LAZY)
    @Valid @NotNull
    @JoinColumn(name = "qs_user_id")
    private User user;

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
    }

    public Question(String name, User user, Integer votes, String content) {
        super(content);
        this.name = name;
        this.user = user;
        this.votes = votes;
    }

    public Question(String name, User user, Integer votes, String content, List<Tag> tags, List<Answer> answers, List<Comment> comments) {
        this(name, user, votes, content);
        this.tags.addAll(tags);
        this.answers.addAll(answers);
        this.comments.addAll(comments);
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


    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags.addAll(tags);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments.addAll(comments);
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
