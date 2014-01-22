package com.dozortsev.adviceexchange.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends AbstractEntity<Long> {

    private String name;
    private Integer age;
    private StringBuilder aboutMe = new StringBuilder();
    private Date joined;
    private String location;
    private String site;
    private String email;
    private String password;
    private Integer reputation = 1;
    private List<Question> questions = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
}
