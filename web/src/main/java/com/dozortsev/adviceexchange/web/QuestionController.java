package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.AnswerService;
import com.dozortsev.adviceexchange.service.CommentService;
import com.dozortsev.adviceexchange.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes({ "question", "user" })
public class QuestionController {

    @Autowired private QuestionService questionService;

    @Autowired private AnswerService answerService;

    @Autowired private CommentService commentService;

    @RequestMapping(value = "/question/{id}")
    public ModelAndView question(@PathVariable Long id) {

        return new ModelAndView("question", "question", questionService.findById(id))
                .addObject("answers", answerService.findAnswersByQuestionId(id))
                .addObject("comments", commentService.findCommentsByQuestionId(id));
    }

    @RequestMapping(value = "/question/answer/create", method = POST)
    public String answer(@ModelAttribute Answer answer,
                         @ModelAttribute User user,
                         @ModelAttribute Question question) {

        answer.setUser(user);
        answer.setQuestion(question);
        answerService.create(answer);

        return "redirect:/question/" + question.getId();
    }
}
