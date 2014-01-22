package com.dozortsev.adviceexchange.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Answer")
public class Answer extends AbstractEntity<Long> {

    private Question question;
    private User user;
    private Integer votes;
    private Date created;
    private StringBuilder content = new StringBuilder(1000);
    private Boolean accepted;
}
