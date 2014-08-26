package com.dozortsev.adviceexchange.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Table(name = "vote")
@AttributeOverride(name = "id", column = @Column(name = "vt_id"))
public class Vote extends AbstractEntity<Long> {

    public static final int UP = 10;

    public static final int DOWN = -2;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "vt_user_id")
    private User user;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "vt_activity_id")
    private UserActivity activity;

    @Column(name = "vt_score")
    private int score;

    @Temporal(TIMESTAMP)
    @Column(name = "vt_created", updatable = false)
    private Date created = new Date();

    public Vote() {
    }

    public Vote(User user, UserActivity activity, int score) {
        this.user = user;
        this.activity = activity;
        this.score = score;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public UserActivity getActivity() {
        return activity;
    }
    public void setActivity(UserActivity activity) {
        this.activity = activity;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Date getCreated() {
        return created;
    }
    @SuppressWarnings("unused")
    public void setCreated(Date created) {
        this.created = created;
    }
}
