package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.service.*;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/test-application-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
public abstract class TestContext {

    protected static Logger log = Logger.getLogger(TestContext.class);

    protected @Autowired TagService tagService;

    protected @Autowired UserService userService;

    protected @Autowired BadgeService badgeService;

    protected @Autowired AnswerService answerService;

    protected @Autowired CommentService commentService;

    protected @Autowired QuestionService questionService;
}