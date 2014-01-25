package com.dozortsev.adviceexchange.domain;

import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "user")
public class User extends AbstractEntity<Long> {

    @NotEmpty @Length(min = 2, max = 50)
    @Column(name = "user_name")
    private String name;

    @Min(6) @Max(150)
    @Column(name = "user_age")
    private Integer age;

    @Length(min = 5, max = 1000)
    @Column(name = "user_about_me")
    private StringBuilder aboutMe = new StringBuilder(1000);

    @NotNull @Temporal(TIMESTAMP)
    @Column(name = "user_joined", updatable = false)
    private Date joined;

    @Length(min = 3, max = 150)
    @Column(name = "user_location")
    private String location;

    @URL(protocol = "http")
    @Length(min = 10, max = 120)
    @Column(name = "user_site")
    private String site;

    @NotBlank @Length(min = 10, max = 120)
    @Email @Column(name = "user_email")
    private String email;

    @NotBlank @Length(min = 5, max = 15)
    @Column(name = "user_password")
    private String password;

    @NotNull @Column(name = "user_reputation")
    private Integer reputation = 1;

    @OneToMany(cascade = REMOVE, mappedBy = "user")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(cascade = REMOVE, mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(cascade = REMOVE, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public User() { }

    public User(String name, Integer age, StringBuilder aboutMe, String location, String site, String email, String password, Integer reputation) {
        this.name = name;
        this.age = age;
        this.aboutMe = aboutMe;
        this.location = location;
        this.site = site;
        this.email = email;
        this.password = password;
        this.reputation += reputation;
    }

    public User(String name, Integer age, StringBuilder aboutMe, String location, String site, String email, String password, Integer reputation, List<Question> questions, List<Answer> answers, List<Comment> comments) {
        this.name = name;
        this.age = age;
        this.aboutMe = aboutMe;
        this.location = location;
        this.site = site;
        this.email = email;
        this.password = password;
        this.reputation += reputation;
        this.questions = questions;
        this.answers = answers;
        this.comments = comments;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public StringBuilder getAboutMe() {
        return aboutMe;
    }
    public void setAboutMe(StringBuilder aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Date getJoined() {
        return joined;
    }
    @SuppressWarnings("unused")
    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getReputation() {
        return reputation;
    }
    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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
