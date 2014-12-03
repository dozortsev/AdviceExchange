package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Question;
import com.dozortsev.adviceexchange.service.AnswerService;
import com.dozortsev.adviceexchange.service.CommentService;
import com.dozortsev.adviceexchange.service.QuestionService;
import com.dozortsev.adviceexchange.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
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
                              @RequestParam(required = false) String words) {

        ModelAndView mav = new ModelAndView("index");

        if (words != null && words.length() > 0) {
            words = words.trim();

            if (Pattern.compile("\\[|\\]]").matcher(words).find()) {

                final Matcher mtr = Pattern.compile("[\\w-]+").matcher(words);

                HashSet<String> tags = new HashSet<String>() {{
                    while (mtr.find()) add(mtr.group().toLowerCase());
                }};
                return new ModelAndView("redirect:/questions/tagged/" + String.join(" ", tags));
            }
            return mav.addObject("questions", questionService.findByKeyWords(words.split("\\s+")));
        }
        return mav.addObject("questions", questionService.loadFrom(page != null ? (page - 1) * 10 : 0))
                  .addObject("questionCount", questionService.totalCount());
    }

    @RequestMapping(value = "/questions/tagged/{tag}", method = GET)
    public ModelAndView searchQuestion(@PathVariable String tag) {
        return new ModelAndView(
                "index", "questions", questionService.findByTags(tag.split("\\s+"))
        );
    }

    @RequestMapping(value = "/question/{id}")
    public ModelAndView question(@PathVariable long id) {

        return new ModelAndView("question", "question", questionService.findById(id))
                .addObject("answers", answerService.findByQuestionId(id))
                .addObject("comments", commentService.findByQuestionId(id));
    }

    @RequestMapping(value = "/answer/delete/{id}", method = GET)
    public String answerDelete(@PathVariable long id) {

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
                        title, content, user, 0, tagService.findByName(tags.split(" "))
                )
        );
    }

    @RequestMapping(value = "/questions/ask", method = GET)
    public ModelAndView askQuestion() {
        return new ModelAndView(
                "ask-question", "tags", tagService.loadAll()
        );
    }

    @RequestMapping(value = "/answer/create", method = POST)
    public String answerCreate(@ModelAttribute User user,
                               @ModelAttribute Question question,
                               @RequestParam   String aswContent)
    {
        questionService.addAnswer(new Answer(user, aswContent, question));

        return "redirect:/question/" + question.getId();
    }

    @RequestMapping(value = "/question/delete/{id}", method = GET)
    public String questionDelete(@PathVariable long id) {

        questionService.deleteById(id);

        return "redirect:/questions";
    }
}
