package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.service.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/test-application-context.xml")
public abstract class TestContext {

    @Autowired protected TagService tagService;

    @Autowired protected UserService userService;

    @Autowired protected BadgeService badgeService;

    @Autowired protected AnswerService answerService;

    @Autowired protected CommentService commentService;

    @Autowired protected QuestionService questionService;
}