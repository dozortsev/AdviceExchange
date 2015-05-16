package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "user")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class User extends AbstractEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "about_me")
    private String aboutMe;

    @Temporal(TIMESTAMP)
    @Column(name = "joined", updatable = false)
    private Date joined = new Date();

    @Column(name = "location")
    private String location;

    @Column(name = "site")
    private String site;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled = Boolean.TRUE;

    @Column(name = "reputation")
    private int reputation = 1;

    @OneToMany(cascade = { MERGE, PERSIST, REMOVE })
    @JoinColumn(name = "id")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(cascade = { MERGE, PERSIST, REMOVE })
    @JoinColumn(name = "id")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(cascade = { MERGE, PERSIST, REMOVE })
    @JoinColumn(name = "id")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "user_badge",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id"))
    private List<Badge> badges = new ArrayList<>();

    public User() {
    }

    public User(String name, int age, String aboutMe, String location, String site, String email, String password, int reputation) {
        this.name = name;
        this.age = age;
        this.aboutMe = aboutMe;
        this.location = location;
        this.site = site;
        this.email = email;
        this.password = password;
        this.reputation = reputation;
    }

    public User(String name, int age, String aboutMe, String location, String site, String email, String password, int reputation, List<Question> questions, List<Answer> answers, List<Comment> comments) {
        this(name, age, aboutMe, location, site, email, password, reputation);
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

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getAboutMe() {
        return aboutMe;
    }
    public void setAboutMe(String aboutMe) {
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

    public int getReputation() {
        return reputation;
    }
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void canEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public List<Badge> getBadges() {
        return badges;
    }
    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public int changeReputation(int reputation) {
        return this.reputation += reputation;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (enabled != user.enabled) return false;
        if (reputation != user.reputation) return false;
        if (!email.equals(user.email)) return false;
        if (!joined.equals(user.joined)) return false;
        if (location != null ? !location.equals(user.location) : user.location != null) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;
        if (site != null ? !site.equals(user.site) : user.site != null) return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + joined.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + reputation;
        return result;
    }
}
