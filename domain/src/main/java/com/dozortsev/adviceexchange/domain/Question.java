package com.dozortsev.adviceexchange.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question extends AbstractEntity<Long> {

    private String name;
    private User user;
    private Integer votes;
    private StringBuilder content = new StringBuilder(1000);
    private Integer views;
    private Date created;
    private List<Tag> tags = new ArrayList<>(5);
    private List<Answer> answers = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
}
