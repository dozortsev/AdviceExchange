package com.dozortsev.adviceexchange.domain;

import java.util.Date;

public class Comment extends AbstractEntity<Long> {

    private Question question;
    private User user;
    private StringBuilder content = new StringBuilder();
    private Date created;
}
