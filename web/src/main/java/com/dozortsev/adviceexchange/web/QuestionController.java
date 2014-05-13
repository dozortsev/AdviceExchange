package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.AnswerService;
import com.dozortsev.adviceexchange.service.CommentService;
import com.dozortsev.adviceexchange.service.QuestionService;
import com.dozortsev.adviceexchange.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes(value = { "user", "question", "questionCount" })
public class QuestionController {

    private @Autowired TagService tagService;

    private @Autowired QuestionService questionService;

    private @Autowired AnswerService answerService;

    private @Autowired CommentService commentService;

    @RequestMapping(value="/questions", method = GET)
    public ModelAndView index(@RequestParam(required = false) Integer page,
                              @RequestParam(required = false) String  keyWords) {

        ModelAndView mav = new ModelAndView("index");

        if (keyWords != null && keyWords.length() > 0) {

            Matcher matcher = Pattern.compile("\\[|\\]]").matcher(keyWords);

            if (matcher.find()) {
                List<String> tags = new ArrayList<>();

                matcher = Pattern.compile("(\\w+-\\w+|\\w+)+").matcher(keyWords);

                if (matcher.find()) for (int i = 0; i < matcher.groupCount(); i++) {
                    tags.add(matcher.group(i));
                }
                return new ModelAndView("redirect:/questions/tagged/" + String.join(" ", tags));
            }
            return mav.addObject("questions", questionService.findQuestionsByKeyWords(keyWords.split("\\s+")));
        }
        return mav.addObject("questions", questionService.loadFrom(page != null ? (page - 1) * 10 : 0))
                  .addObject("questionCount", questionService.totalCount());
    }

    @RequestMapping(value = "/questions/tagged/{tag}", method = GET)
    public ModelAndView searchQuestion(@PathVariable String tag) {

        Set<Question> questions = questionService.findQuestionsByTags(tag.split("\\s+"));

        return new ModelAndView("index", "questions", questions);
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
                                 @RequestParam   String tags,
                                 @RequestParam   String title,
                                 @RequestParam   String content)
    {
        return "redirect:/question/" + questionService.create(
                new Question(
                        user, content, title, tagService.findTagByName(tags.split(" "))
                )
        );
    }

    @RequestMapping(value = "/questions/ask", method = GET)
    public ModelAndView askQuestion() {

        return new ModelAndView("ask-question", "tags", tagService.loadAll());
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
