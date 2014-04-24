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

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes(value = { "user", "question", "questionCount" })
public class QuestionController {

    private @Autowired QuestionService questionService;

    private @Autowired AnswerService answerService;

    private @Autowired CommentService commentService;

    @RequestMapping(value="/questions", method = GET)
    public ModelAndView index(@RequestParam(required = false) Integer page) {

        return new ModelAndView("index", "questionCount", questionService.totalCount())
                .addObject("questions", questionService.loadFrom(
                                page != null ? (page - 1) * 10 : 0)  // offset
        );
    }

    @RequestMapping(value = "/questions/tagged/{tags}", method = GET)
    public ModelAndView searchQuestion(@PathVariable String tags) {

        Set<Question> questions = questionService.findQuestionsByTags(tags.split(" "));

        return new ModelAndView("index", "questionCount", questionService.totalCount())
                .addObject("questions", questions);
    }

    @RequestMapping(value = "/question/{id}")
    public ModelAndView question(@PathVariable Long id) {

        return new ModelAndView("question", "question", questionService.findById(id))
                .addObject("answers", answerService.findAnswersByQuestionId(id))
                .addObject("comments", commentService.findCommentsByQuestionId(id));
    }

    @RequestMapping(value = "/answer/delete/{id}", method = GET)
    public String answerDelete(@PathVariable Long id) {

        Answer answer = answerService.findById(id);

        questionService.delAnswer(answer);

        return "redirect:/question/" + answer.getQuestion().getId();
    }

    @RequestMapping(value = "/questions/create", method = POST)
    public String questionCreate(@ModelAttribute User user,
                                 @RequestParam   String title,
                                 @RequestParam   String content)
    {
        Question question = new Question(user, content, title, null);
        questionService.create(question);

        return "redirect:/question/" + question.getId();
    }

    @RequestMapping(value = "/answer/create", method = POST)
    public String answerCreate(@ModelAttribute User user,
                               @ModelAttribute Question question,
                               @RequestParam   String aswContent)
    {
        questionService.addAnswer(question, new Answer(user, aswContent, question, Boolean.FALSE));

        return "redirect:/question/" + question.getId();
    }

    @RequestMapping(value = "/question/delete/{id}", method = GET)
    public String questionDelete(@PathVariable Long id) {

        questionService.deleteById(id);

        return "redirect:/questions";
    }
}
