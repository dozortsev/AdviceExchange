package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.service.AnswerService;
import com.dozortsev.adviceexchange.service.CommentService;
import com.dozortsev.adviceexchange.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @RequestMapping(value = "/question/delete/{id}", method = GET)
    public String questionDelete(@PathVariable Long id) {

        questionService.deleteById(id);

        return "redirect:/questions";
    }
}
