package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.AnswerService;
import com.dozortsev.adviceexchange.service.CommentService;
import com.dozortsev.adviceexchange.service.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes(value = { "user", "question", "questionCount" })
public class QuestionController {

    private @Autowired QuestionService questionService;

    private @Autowired AnswerService answerService;

    private @Autowired CommentService commentService;

    private static final Logger log = Logger.getLogger(QuestionController.class);

    @RequestMapping(value="/questions", method = GET)
    public ModelAndView index(@RequestParam(required = false) Integer page,
                              @RequestParam(required = false) String keyWords) {

        ModelAndView mav = new ModelAndView("index", "questionCount", questionService.totalCount());

        if (keyWords != null && keyWords.length() > 0) {

            Matcher matcher = Pattern.compile("(\\p{Alnum}+)").matcher(keyWords);

            if (matcher.find()) {

                String url = "redirect:/questions/tagged/";

                for (int i = 0; i < matcher.groupCount(); i++)
                    url += matcher.group(i);

                return new ModelAndView(url);
            }
            return mav.addObject("questions", questionService.findQuestionsByKeyWords(keyWords.split("\\s+")));
        }

        return mav.addObject("questions", questionService.loadFrom(page != null ? (page - 1) * 10 : 0));
    }

    @RequestMapping(value = "/questions/tagged/{tag}", method = GET)
    public ModelAndView searchQuestion(@PathVariable String tag) {

        Set<Question> questions = questionService.findQuestionsByTags(tag.split("\\s+"));

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
        questionService.addAnswer(question, new Answer(user, aswContent, question));

        return "redirect:/question/" + question.getId();
    }

    @RequestMapping(value = "/question/delete/{id}", method = GET)
    public String questionDelete(@PathVariable Long id) {

        questionService.deleteById(id);

        return "redirect:/questions";
    }
}
