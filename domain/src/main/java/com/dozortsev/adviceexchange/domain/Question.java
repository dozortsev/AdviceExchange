package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "question")
@PrimaryKeyJoinColumn(name = "qs_id")
public class Question extends UserActivity {

    @Column(name = "qs_name")
    private String name;

    @Column(name = "qs_votes")
    private Integer votes = 0;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "question_tag",
            joinColumns = @JoinColumn(name = "qt_question_id"),
            inverseJoinColumns = @JoinColumn(name = "qt_tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();

    public Question() {
        super();
        setType(Type.QUESTION);
    }

    public Question(User user, String content, String name, List<Tag> tags) {
        super(user, Type.QUESTION, content);
        this.name = name;
        this.tags = tags;
    }

    public Question(User user, String content, String name, List<Tag> tags, List<Answer> answers, List<Comment> comments) {
        this(user, content, name, tags);
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
}
