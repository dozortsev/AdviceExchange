package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.AnswerService;
import com.dozortsev.adviceexchange.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes({ "user", "question" })
public class AnswerController {

    @Autowired private AnswerService answerService;

    @Autowired private QuestionService questionService;

    @RequestMapping(value = "/answer/delete/{id}", method = GET)
    public String answerDelete(@PathVariable Long id) {

        Answer answer = answerService.findById(id);

        questionService.delAnswer(answer.getQuestion(), answer);

        return "redirect:/question/" + answer.getQuestion().getId();
    }

    @RequestMapping(value = "/question/create/answer", method = POST)
    public String answerCreate(@ModelAttribute Answer answer,
                               @ModelAttribute User user,
                               @ModelAttribute Question question) {

        answer.setUser(user);
        answer.setQuestion(question);
        questionService.addAnswer(question, answer);

        return "redirect:/question/" + question.getId();
    }
}
