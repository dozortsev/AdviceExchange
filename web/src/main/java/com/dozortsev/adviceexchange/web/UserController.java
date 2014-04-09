package com.dozortsev.adviceexchange.web;

import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.service.BadgeService;
import com.dozortsev.adviceexchange.service.QuestionService;
import com.dozortsev.adviceexchange.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes({ "user", "activities" })
public class UserController {

    @Autowired private UserService userService;

    @Autowired private BadgeService badgeService;

    @Autowired private QuestionService questionService;

    private static final Logger log = getLogger(UserController.class);

    @RequestMapping(value="/welcome", method = GET)
    public ModelAndView printWelcome(Principal principal) {

        User user = userService.findUserByLogin(principal.getName());

        return new ModelAndView("index", "user", user).addObject("map", questionService.loadAll(0, 10));
    }

    @RequestMapping(value = "/questions", method = GET)
    public ModelAndView findQuestions(@RequestParam String tags) {

        Set<Question> questions = questionService.findQuestionsByTags(tags.split(" "));

        return new ModelAndView("index", "questions", questions);
    }

    @RequestMapping(value = "/createAccount", method = POST)
    public String createAccount(@ModelAttribute("newUser") User user) {

        user.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), null));
        user.getBadges().add(badgeService.findById(2L)); // User badge

        userService.create(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/login/failed", method = GET)
    public String loginFail(Model m) {

        m.addAttribute("error", true);

        return "login";
    }
}
