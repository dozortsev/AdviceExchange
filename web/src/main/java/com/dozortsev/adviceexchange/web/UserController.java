package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes({ "user", "map", "questionCount", "userCount" })
public class UserController {

    @Autowired private UserService userService;

    @Autowired private BadgeService badgeService;

    @Autowired private AnswerService answerService;

    @Autowired private CommentService commentService;

    @Autowired private QuestionService questionService;

    @Autowired private Md5PasswordEncoder encoder;

    private static final Logger log = getLogger(UserController.class);

    @RequestMapping(value = "/users/login", method = GET)
    public ModelAndView logIn(Principal principal) {

        return new ModelAndView("redirect:/questions", "user", userService.findUserByLogin(principal.getName()));
    }

    @RequestMapping(value = "/users", method = GET)
    public ModelAndView users(@RequestParam(required = false) Integer page,
                              @RequestParam(required = false) String name) {

        return new ModelAndView("users", "userCount", userService.totalCount())
                .addObject("users", userService.findUsersByName(
                name != null ? name : "", page != null ? (page - 1) * 36 : 0)
        );
    }

    @RequestMapping(value="/questions", method = GET)
    public ModelAndView index(@RequestParam(required = false) Integer page) {

        return new ModelAndView("index", "questions", questionService.loadFrom(page != null ? (page - 1) * 10 : 0))
                .addObject("questionCount", questionService.totalCount());
    }

    @RequestMapping(value = "/user/{id}", method = GET)
    public ModelAndView user(@PathVariable Long id) {

        return new ModelAndView("user", "member", userService.findById(id))
                .addObject("questions", questionService.findQuestionsByUserId(id))
                .addObject("answers", answerService.findAnswersByUserId(id));
    }

    @RequestMapping(value = "/questions/create", method = POST)
    public String askQuestion(@ModelAttribute Question ask, @ModelAttribute User user) {

        ask.setUser(user);
        questionService.create(ask);

        return "redirect:/question/" + ask.getId();
    }

    @RequestMapping(value = "/search", method = GET)
    public ModelAndView findQuestions(@RequestParam String tags) {

        Set<Question> questions = questionService.findQuestionsByTags(tags.split(" "));

        return new ModelAndView("index", "questions", questions);
    }

    @RequestMapping(value = "/createAccount", method = POST)
    public String createAccount(@ModelAttribute("member") User member) {

        member.setPassword(encoder.encodePassword(member.getPassword(), null));
        member.getBadges().add(badgeService.findById(2L)); // USER badge

        userService.create(member);

        return "redirect:/login";
    }

    @RequestMapping(value = "/login/failed", method = GET)
    public String loginFail(Model m) {

        m.addAttribute("error", true);

        return "login";
    }
}
