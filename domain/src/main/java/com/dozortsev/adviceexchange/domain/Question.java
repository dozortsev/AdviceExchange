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

    @Column(name = "qs_title")
    private String title;

    @Column(name = "qs_votes")
    private int votes = 0;

    @Column(name = "qs_asw_count", nullable = false)
    private int answerCount = 0;

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
    }

    public Question(String title, String content, User user, int answerCount, int votes, List<Tag> tags) {
        super(user, Type.QUESTION, content);
        this.title = title;
        this.votes = votes;
        this.answerCount = answerCount;
        this.tags = tags;
    }

    public Question(User user, String content, int answerCount, int votes, String title, List<Tag> tags, List<Answer> answers, List<Comment> comments) {
        this(title, content, user, answerCount, votes, tags);
        this.answers = answers;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getVotes() {
        return votes;
    }
    public void setVotes(int votes) {
        this.votes = votes;
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getAnswerCount() {
        return answerCount;
    }
    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
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

    public int changeVotes(int votes) {
        return this.votes += votes;
    }
}
